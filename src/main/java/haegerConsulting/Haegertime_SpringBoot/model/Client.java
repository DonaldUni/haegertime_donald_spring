package haegerConsulting.Haegertime_SpringBoot.model;

import java.util.ArrayList;

public class Client extends Person{

    private long clientId;
    private String enterpriseName;
    private ArrayList<Project> projects;

    //Constructor
    public Client() {
    }

    public Client(String lastname, String firstname, String enterpriseName, ArrayList<Project> projects) {
        super(lastname, firstname);
        this.enterpriseName = enterpriseName;
        this.projects = projects;
    }

    public Client(long id, String lastname, String firstname, long clientId, String enterpriseName, ArrayList<Project> projects) {
        super(id, lastname, firstname);
        this.clientId = clientId;
        this.enterpriseName = enterpriseName;
        this.projects = projects;
    }


    //getter und setter
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId = " + clientId +
                ", enterpriseName = '" + enterpriseName + '\'' +
                ", projects = " + projects +
                '}';
    }
}
