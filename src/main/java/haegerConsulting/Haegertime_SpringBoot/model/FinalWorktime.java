package haegerConsulting.Haegertime_SpringBoot.model;

import haegerConsulting.Haegertime_SpringBoot.model.builder.FinalWorktimeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "FinalWorktime")
@SequenceGenerator(name = "generator", initialValue = 1)
public class FinalWorktime {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Long id;

    @ManyToOne
    @JoinColumns(
            {
            @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "project_name", referencedColumnName = "name", nullable = false)
            }
    )
    private Project project;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @Column(nullable = false)
    private float workhour;

    @Column(nullable = false)
    private float overtime;

    @Column(nullable = false)
    private float undertime;

    @Column(nullable = false)
    private String period;

    //Constructor
    public FinalWorktime(FinalWorktimeBuilder builder){

        id = builder.id;
        project = builder.project;
        user = builder.user;
        workhour = builder.workhour;
        overtime = builder.overtime;
        undertime = builder.undertime;
        period = builder.period;
    }


    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", project = " + project.toString() +
                ", user = " + user.toString() +
                ", workhour = " + workhour +
                ", overtime = " + overtime +
                ", undertime = " + undertime +
                ", period = '" + period + '\'' +
                '}';
    }
}
