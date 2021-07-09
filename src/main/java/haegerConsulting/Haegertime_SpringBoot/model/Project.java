package haegerConsulting.Haegertime_SpringBoot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
@SequenceGenerator(name = "generator", initialValue = 1)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns(
            {
                    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false),
                    @JoinColumn(name = "project_name", referencedColumnName = "name", nullable = false)
            }
    )
    private List<User> users = new ArrayList<>();


    //Constructor
    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description, ArrayList<User> users) {
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public Project(Long id, String name, String description, ArrayList<User> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
    }


    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
