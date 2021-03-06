package haegerConsulting.Haegertime_SpringBoot.model;

import haegerConsulting.Haegertime_SpringBoot.model.builder.UserBuilder;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "User")
//@SequenceGenerator(name = "generator", initialValue = 1)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false, unique = true)
    private long employeeNummer;


    @Column(nullable = false, unique = true)
    private String userName;

    @Size(min = 2, message = "Password should have minimum 5 characters")
    @Size(max = 20, message = "Password should have maximum 10 characters")
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
    private float numberOfHoliday;

    //Constructor
    public User(){ }

    public User(UserBuilder builder){
        this.id = builder.id;
        this.lastname = builder.lastname;
        this.firstname = builder.firstname;
        employeeNummer = builder.employeeNummer;
        userName = builder.userName;
        password = builder.password;
        email = builder.email;
        power = builder.power;
        status = builder.status;
        numberOfUsedHoliday = builder.numberOfUsedHoliday;
        numberOfRestHoliday = builder.numberOfRestHoliday;
        numberOfHoliday = builder.numberOfHoliday;
    }


    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

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

    public float getNumberOfHoliday() {
        return numberOfHoliday;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", employeeNummer=" + employeeNummer +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", power=" + power +
                ", status=" + status +
                ", numberOfUsedHoliday=" + numberOfUsedHoliday +
                ", numberOfRestHoliday=" + numberOfRestHoliday +
                ", numberOfSickDay=" + numberOfSickDay +
                ", NUMBEROFHOLIDAY=" + numberOfHoliday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return employeeNummer == user.employeeNummer && Float.compare(user.numberOfUsedHoliday, numberOfUsedHoliday) == 0 && Float.compare(user.numberOfRestHoliday, numberOfRestHoliday) == 0 && Float.compare(user.numberOfSickDay, numberOfSickDay) == 0 && Float.compare(user.numberOfHoliday, numberOfHoliday) == 0 && id.equals(user.id) && lastname.equals(user.lastname) && firstname.equals(user.firstname) && userName.equals(user.userName) && password.equals(user.password) && email.equals(user.email) && power == user.power && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, employeeNummer, userName, password, email, power, status, numberOfUsedHoliday, numberOfRestHoliday, numberOfSickDay, numberOfHoliday);
    }
}
