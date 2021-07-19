package haegerConsulting.Haegertime_SpringBoot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer extends Person{

    @Column(unique = true, nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String enterpriseName;

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    //Constructor
    public Customer() { }

    public Customer(Long customerId, String lastname, String firstname, String enterpriseName, List<Project> project) {
        super(lastname, firstname);
        this.customerId = customerId;
        this.enterpriseName = enterpriseName;
        this.projects = project;
    }

    public Customer(Long person_id, String lastname, String firstname, long customerId, String enterpriseName, List<Project> project) {
        super(person_id, lastname, firstname);
        this.customerId = customerId;
        this.enterpriseName = enterpriseName;
        this.projects = project;
    }


    //getter und setter
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId = " + customerId +
                ", enterpriseName = '" + enterpriseName + '\'' +
                ", projects = " + projects +
                '}';
    }
}
