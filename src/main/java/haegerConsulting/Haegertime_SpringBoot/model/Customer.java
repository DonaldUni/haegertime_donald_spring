package haegerConsulting.Haegertime_SpringBoot.model;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
//@SequenceGenerator(name = "generator", initialValue = 1)
public class Customer extends Person{

    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @Column(unique = true, nullable = false)
    private Long customer_id;

    @Column(nullable = false)
    private String enterpriseName;

    @Column(nullable = false)
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Project> projects = new ArrayList<>();

    //Constructor
    public Customer() {
    }

    public Customer(String lastname, String firstname, String enterpriseName, List<Project> project) {
        super(lastname, firstname);
        this.enterpriseName = enterpriseName;
        this.projects = project;
    }

    public Customer(Long person_id, String lastname, String firstname, long customer_id, String enterpriseName, List<Project> project) {
        super(person_id, lastname, firstname);
        this.customer_id = customer_id;
        this.enterpriseName = enterpriseName;
        this.projects = project;
    }


    //getter und setter
    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
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
                "clientId = " + customer_id +
                ", enterpriseName = '" + enterpriseName + '\'' +
                ", projects = " + projects +
                '}';
    }
}
