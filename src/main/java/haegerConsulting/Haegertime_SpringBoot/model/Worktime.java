package haegerConsulting.Haegertime_SpringBoot.model;

import haegerConsulting.Haegertime_SpringBoot.model.builder.WorktimeBuilder;

public class Worktime {

    private long id;
    private long projectId;
    private String projectName;
    private long employeeNummer;
    private float workhour;
    private float overtime;
    private float undertime;
    private String period;

    //Constructor
    public Worktime(WorktimeBuilder builder){

        id = builder.id;
        projectId = builder.projectId;
        projectName = builder.projectName;
        employeeNummer = builder.employeeNummer;
        workhour = builder.workhour;
        overtime = builder.overtime;
        undertime = builder.undertime;
        period = builder.period;
    }


    //getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getEmployeeNummer() {
        return employeeNummer;
    }

    public void setEmployeeNummer(long employeeNummer) {
        this.employeeNummer = employeeNummer;
    }

    public float getWorkhour() {
        return workhour;
    }

    public void setWorkhour(float workhour) {
        this.workhour = workhour;
    }

    public float getOvertime() {
        return overtime;
    }

    public void setOvertime(float overtime) {
        this.overtime = overtime;
    }

    public float getUndertime() {
        return undertime;
    }

    public void setUndertime(float undertime) {
        this.undertime = undertime;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Worktime{" +
                "id = " + id +
                ", projectId = " + projectId +
                ", projectName = '" + projectName + '\'' +
                ", employeeNummer = " + employeeNummer +
                ", workhour = " + workhour +
                ", overtime = " + overtime +
                ", undertime = " + undertime +
                ", period = '" + period + '\'' +
                '}';
    }
}
