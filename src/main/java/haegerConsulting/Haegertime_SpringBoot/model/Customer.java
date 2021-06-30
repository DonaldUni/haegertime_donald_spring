package haegerConsulting.Haegertime_SpringBoot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
@SequenceGenerator(name = "generator", initialValue = 1)
public class Customer extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private long id;

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

    public Customer(long id, String lastname, String firstname, long clientId, String enterpriseName, List<Project> project) {
        super(id, lastname, firstname);
        this.id = clientId;
        this.enterpriseName = enterpriseName;
        this.projects = project;
    }


    //getter und setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                "clientId = " + id +
                ", enterpriseName = '" + enterpriseName + '\'' +
                ", projects = " + projects +
                '}';
    }
}
