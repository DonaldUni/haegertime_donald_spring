package haegerConsulting.Haegertime_SpringBoot.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    private String lastname;
    private String firstname;

    //Constructor
    public Person() {
    }

    public Person(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Person(long id, String lastname, String firstname) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }


    //getter and setter
    public long getCustomer_id() { return id; }

    public void setCustomer_id(long customer_id) {
        this.id = customer_id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}
