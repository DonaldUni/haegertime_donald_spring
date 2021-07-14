package haegerConsulting.Haegertime_SpringBoot.model.builder;

import haegerConsulting.Haegertime_SpringBoot.model.Project;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.WorktimeType;

public class WorktimeBuilder {

    public Long id;
    public Project project;
    public User user;
    public float workhour;
    public float overtime;
    public float undertime;
    public String period;
    public WorktimeType worktimeType = WorktimeType.Unfinal;

    //Constructor
    public Worktime build(){

//        if (project == null){
//            throw new IllegalStateException(" project is null!");
//        }
//        if (user == null){
//            throw new IllegalStateException(" user is null!");
//        }
        if (workhour == 0){
            throw new IllegalStateException(" workhour is null!");
        }
        if (period == null){
            throw new IllegalStateException(" period is null!");
        }

        return new Worktime(this);
    }


    public WorktimeBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public WorktimeBuilder project(Project project) {
        this.project = project;
        return this;
    }

    public WorktimeBuilder user(User user) {
        this.user = user;
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

    public WorktimeBuilder type(WorktimeType type) {
        this.worktimeType = type;
        return this;
    }
}
