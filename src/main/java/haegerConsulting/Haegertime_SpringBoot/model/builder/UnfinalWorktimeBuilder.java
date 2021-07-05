package haegerConsulting.Haegertime_SpringBoot.model.builder;

import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.model.Project;
import haegerConsulting.Haegertime_SpringBoot.model.UnfinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.model.User;

public class UnfinalWorktimeBuilder {

    public Long id;
    public Project project;
    public User user;
    public float workhour;
    public float overtime;
    public float undertime;
    public String period;

    //Constructor
    public UnfinalWorktime build(){

        if (project == null){
            throw new IllegalStateException(" project is null!");
        }
        if (user == null){
            throw new IllegalStateException(" user is null!");
        }
        if (workhour == 0){
            throw new IllegalStateException(" workhour is null!");
        }
        if (period == null){
            throw new IllegalStateException(" period is null!");
        }

        return new UnfinalWorktime(this);
    }


    public UnfinalWorktimeBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UnfinalWorktimeBuilder project(Project project) {
        this.project = project;
        return this;
    }

    public UnfinalWorktimeBuilder user(User user) {
        this.user = user;
        return this;
    }

    public UnfinalWorktimeBuilder workhour(float workhour) {
        this.workhour = workhour;
        return this;
    }

    public UnfinalWorktimeBuilder overtime(float overtime) {
        this.overtime = overtime;
        return this;
    }

    public UnfinalWorktimeBuilder undertime(float undertime) {
        this.undertime = undertime;
        return this;
    }

    public UnfinalWorktimeBuilder period(String period) {
        this.period = period;
        return this;
    }
}
