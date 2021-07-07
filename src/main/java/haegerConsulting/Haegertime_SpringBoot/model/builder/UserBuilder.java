package haegerConsulting.Haegertime_SpringBoot.model.builder;

import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;

public class UserBuilder {

    public Long personId;
    public String lastname;
    public String firstname;
    public long employeeNummer;
    public String userName;
    public String password;
    public String email;
    public Power power = Power.Employee;
    public Status status = Status.actived;

    public float numberOfUsedHoliday = 0;
    public float numberOfRestHoliday = 30;
    public float numberOfSickDay = 0 ;
    public final int NUMBEROFHOLIDAY = 30;

    public User build(){

        if (userName == null){
            throw new IllegalStateException(" username is null!");
        }
        if (password == null){
            throw new IllegalStateException(" password is null!");
        }
        if (email == null){
            throw new IllegalStateException(" email is null!");
        }

        return new User(this);
    }


    // setter for Builder
    public UserBuilder personId(Long personId) {
        this.personId = personId;
        return this;
    }

    public UserBuilder lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserBuilder firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder employeeNummer(long employeeNummer) {
        this.employeeNummer = employeeNummer;
        return this;
    }

    public UserBuilder userName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder power(Power power) {
        this.power = power;
        return this;
    }

    public UserBuilder status(Status status) {
        this.status = status;
        return this;
    }

    public UserBuilder numberOfUsedHoliday(float numberOfUsedHoliday) {
        this.numberOfUsedHoliday = numberOfUsedHoliday;
        return this;
    }

    public UserBuilder numberOfRestHoliday(float numberOfRestHoliday) {
        this.numberOfRestHoliday = numberOfRestHoliday;
        return this;
    }

    public UserBuilder numberOfSickDay(float numberOfSickDay) {
        this.numberOfSickDay = numberOfSickDay;
        return this;
    }


}
