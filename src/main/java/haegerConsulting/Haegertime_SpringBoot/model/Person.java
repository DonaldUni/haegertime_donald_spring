package haegerConsulting.Haegertime_SpringBoot.model;

public abstract class Person {

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
    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
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
