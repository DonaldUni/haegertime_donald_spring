package haegerConsulting.Haegertime_SpringBoot.model;

import haegerConsulting.Haegertime_SpringBoot.model.enumerations.RequestStatus;


import java.time.Instant;

public class RequestOfHoliday {

    private long requestId;
    private long employeeNummer;
    private int numberOfRequestedDay;
    private Instant startDate;
    private Instant finishDate;
    private RequestStatus status = RequestStatus.Pending ;
    private Instant time;

    //Constructor
    public RequestOfHoliday(long employeeNummer, int numberOfRequestedDay, Instant startDate, Instant finishDate, RequestStatus status) {
        this.employeeNummer = employeeNummer;
        this.numberOfRequestedDay = numberOfRequestedDay;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.status = status;
        this.time = Instant.now();
    }

    public RequestOfHoliday(long requestId, long employeeNummer, int numberOfRequestedDay, Instant startDate, Instant finishDate, RequestStatus status, Instant time) {
        this.requestId = requestId;
        this.employeeNummer = employeeNummer;
        this.numberOfRequestedDay = numberOfRequestedDay;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.status = status;
        this.time = time;
    }

    //getter and setter

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getEmployeeNummer() {
        return employeeNummer;
    }

    public void setEmployeeNummer(long employeeNummer) {
        this.employeeNummer = employeeNummer;
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
                "requestId = " + requestId +
                ", employeeNummer = " + employeeNummer +
                ", numberOfRequestedDay = " + numberOfRequestedDay +
                ", startDate = " + startDate +
                ", finishDate = " + finishDate +
                ", status = " + status +
                ", time = " + time +
                '}';
    }
}
