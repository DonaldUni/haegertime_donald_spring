package haegerConsulting.Haegertime_SpringBoot.model;

import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;

public class User extends Person{

    private long employeeNummer;
    private String userName;
    private String password;
    private String email;
    private Power power = Power.Employee;
    private Status status = Status.actived;

    private float numberOfUsedHoliday = 0;
    private float numberOfRestHoliday = 0;
    private float numberOfSickDay = 0 ;
    private final int NUMBEROFHOLIDAY = 30;

    //Constructor
    public User(String lastname, String firstname, String userName, String password, String email) {
        super(lastname, firstname);
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(long id, String lastname, String firstname, long employeeNummer, String userName, String password,
                String email, Power power, Status status, float numberOfUsedHoliday, float numberOfRestHoliday,
                float numberOfSickDay) {

        super(id, lastname, firstname);
        this.employeeNummer = employeeNummer;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.power = power;
        this.status = status;
        this.numberOfUsedHoliday = numberOfUsedHoliday;
        this.numberOfRestHoliday = numberOfRestHoliday;
        this.numberOfSickDay = numberOfSickDay;
    }

    //getter and setter
    public long getEmployeeNummer() {
        return employeeNummer;
    }

    public void setEmployeeNummer(long employeeNummer) {
        this.employeeNummer = employeeNummer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public float getNumberOfUsedHoliday() {
        return numberOfUsedHoliday;
    }

    public void setNumberOfUsedHoliday(float numberOfUsedHoliday) {
        this.numberOfUsedHoliday = numberOfUsedHoliday;
    }

    public float getNumberOfRestHoliday() {
        return numberOfRestHoliday;
    }

    public void setNumberOfRestHoliday(float numberOfRestHoliday) {
        this.numberOfRestHoliday = numberOfRestHoliday;
    }

    public float getNumberOfSickDay() {
        return numberOfSickDay;
    }

    public void setNumberOfSickDay(float numberOfSickDay) {
        this.numberOfSickDay = numberOfSickDay;
    }

    public int getNUMBEROFHOLIDAY() {
        return NUMBEROFHOLIDAY;
    }
}
