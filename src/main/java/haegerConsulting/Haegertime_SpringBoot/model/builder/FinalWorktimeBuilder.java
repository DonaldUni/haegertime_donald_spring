package haegerConsulting.Haegertime_SpringBoot.model.builder;

import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.model.Project;
import haegerConsulting.Haegertime_SpringBoot.model.User;

import java.util.List;

public class FinalWorktimeBuilder {

    public long id;
    public Project project;
    public User user;
    public float workhour;
    public float overtime;
    public float undertime;
    public String period;

    //Constructor
    public FinalWorktime build(){

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

        return new FinalWorktime(this);
    }


    public FinalWorktimeBuilder id(long id) {
        this.id = id;
        return this;
    }

    public FinalWorktimeBuilder project(Project project) {
        this.project = project;
        return this;
    }

    public FinalWorktimeBuilder user(User user) {
        this.user = user;
        return this;
    }

    public FinalWorktimeBuilder workhour(float workhour) {
        this.workhour = workhour;
        return this;
    }

    public FinalWorktimeBuilder overtime(float overtime) {
        this.overtime = overtime;
        return this;
    }

    public FinalWorktimeBuilder undertime(float undertime) {
        this.undertime = undertime;
        return this;
    }

    public FinalWorktimeBuilder period(String period) {
        this.period = period;
        return this;
    }
}
