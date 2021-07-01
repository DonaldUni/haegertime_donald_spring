package haegerConsulting.Haegertime_SpringBoot.model;

import haegerConsulting.Haegertime_SpringBoot.model.builder.UserBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
@SequenceGenerator(name = "generator", initialValue = 1)
public class User extends Person{

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column(nullable = false, unique = true)
    private long employeeNummer;



    @Column(nullable = false, unique = true)
    private String userName;

    @Size(min = 5, message = "Password should have minimum 5 characters")
    @Size(max = 10, message = "Password should have maximum 10 characters")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Power power;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private float numberOfUsedHoliday;

    @Column(nullable = false)
    private float numberOfRestHoliday;

    @Column(nullable = false)
    private float numberOfSickDay;

    @Column(nullable = false)
    private final int NUMBEROFHOLIDAY;

    //Constructor
    public User(UserBuilder builder){
        super(builder.personId, builder.lastname, builder.firstname);
        employeeNummer = builder.employeeNummer;
        userName = builder.userName;
        password = builder.password;
        email = builder.email;
        power = builder.power;
        status = builder.status;
        numberOfUsedHoliday = builder.numberOfUsedHoliday;
        numberOfRestHoliday = builder.numberOfRestHoliday;
        NUMBEROFHOLIDAY = builder.NUMBEROFHOLIDAY;
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
