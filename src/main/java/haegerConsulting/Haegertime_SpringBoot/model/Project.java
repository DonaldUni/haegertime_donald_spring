package haegerConsulting.Haegertime_SpringBoot.model;

import java.util.ArrayList;

public class Project {

    private long id;
    private String name;
    private String description;
    private ArrayList<User> users;


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

    public Project(long id, String name, String description, ArrayList<User> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
    }


    //getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
