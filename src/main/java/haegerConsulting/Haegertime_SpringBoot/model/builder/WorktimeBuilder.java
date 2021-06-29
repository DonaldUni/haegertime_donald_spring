package haegerConsulting.Haegertime_SpringBoot.model.builder;

import haegerConsulting.Haegertime_SpringBoot.model.Worktime;

public class WorktimeBuilder {

    public long id;
    public long projectId;
    public String projectName;
    public long employeeNummer;
    public float workhour;
    public float overtime;
    public float undertime;
    public String period;

    //Constructor
    public Worktime build(){

        if (projectName == null){
            throw new IllegalStateException(" projectName is null!");
        }
        if (employeeNummer == 0){
            throw new IllegalStateException(" employeeNummer is null!");
        }
        if (workhour == 0){
            throw new IllegalStateException(" workhour is null!");
        }
        if (period == null){
            throw new IllegalStateException(" period is null!");
        }

        return new Worktime(this);
    }


    public WorktimeBuilder id(long id) {
        this.id = id;
        return this;
    }

    public WorktimeBuilder projectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    public WorktimeBuilder projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public WorktimeBuilder employeeNummer(long employeeNummer) {
        this.employeeNummer = employeeNummer;
        return this;
    }

    public WorktimeBuilder workhour(float workhour) {
        this.workhour = workhour;
        return this;
    }

    public WorktimeBuilder overtime(float overtime) {
        this.overtime = overtime;
        return this;
    }

    public WorktimeBuilder undertime(float undertime) {
        this.undertime = undertime;
        return this;
    }

    public WorktimeBuilder period(String period) {
        this.period = period;
        return this;
    }
}
