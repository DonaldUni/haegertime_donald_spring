package haegerConsulting.Haegertime_SpringBoot.model;

import haegerConsulting.Haegertime_SpringBoot.model.enumerations.RequestStatus;


import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "RequestOfHoliday")
@SequenceGenerator(name = "generator", initialValue = 1)
public class RequestOfHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Long id;


    @ManyToOne
    @JoinColumn(
            name = "user_employeeNummer"
    )
    private User user;

    @Column
    private int numberOfRequestedDay;

    @Column
    private Instant startDate;

    @Column
    private Instant finishDate;

    @Column
    private RequestStatus status = RequestStatus.Pending;

    @Column
    private Instant time;

    //Constructor
    public RequestOfHoliday(){ }

    public RequestOfHoliday(int numberOfRequestedDay, Instant startDate, Instant finishDate) {
        this.numberOfRequestedDay = numberOfRequestedDay;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public RequestOfHoliday(User user, int numberOfRequestedDay, Instant startDate, Instant finishDate) {
        this.user = user;
        this.numberOfRequestedDay = numberOfRequestedDay;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.time = Instant.now();
    }

    public RequestOfHoliday(User user, int numberOfRequestedDay, Instant startDate, Instant finishDate, RequestStatus status) {
        this.user = user;
        this.numberOfRequestedDay = numberOfRequestedDay;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.status = status;
        this.time = Instant.now();
    }

    public RequestOfHoliday(Long id, User user, int numberOfRequestedDay, Instant startDate, Instant finishDate, RequestStatus status, Instant time) {
        this.id = id;
        this.user = user;
        this.numberOfRequestedDay = numberOfRequestedDay;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.status = status;
        this.time = time;
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfRequestedDay() {
        return numberOfRequestedDay;
    }

    public void setNumberOfRequestedDay(int numberOfRequestedDay) {
        this.numberOfRequestedDay = numberOfRequestedDay;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Instant finishDate) {
        this.finishDate = finishDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "RequestOfHoliday{" +
                "requestId = " + id +
                ", user = " + user.toString() +
                ", numberOfRequestedDay = " + numberOfRequestedDay +
                ", startDate = " + startDate +
                ", finishDate = " + finishDate +
                ", status = " + status +
                ", time = " + time +
                '}';
    }
}
