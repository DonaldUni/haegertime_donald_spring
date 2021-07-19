package haegerConsulting.Haegertime_SpringBoot.controller;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.facade.EmployeeFacade;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping ("/API/Haegertime/users")
public class EmployeeController {

    @Autowired
    private EmployeeFacade employeeFacade;


    //Get
    @GetMapping
    public Iterable<User> getAllUser(){ return employeeFacade.getAllUser(); }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable(value = "id") Long id){

        User user = null;

        try {
             user = employeeFacade.getUser(id);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    @GetMapping("/getMyAccountData/{id}")
    public String getMyAccountData(@PathVariable(value = "id") Long id){
        String accountData = "";

        try {
            User user = employeeFacade.getUser(id);
            accountData = "My Account { \n" +
                    "personId=" + user.getId() +"\n"+
                    "employeeNummer=" + user.getEmployeeNummer() +"\n"+
                    "userName='" + user.getUserName() + '\'' +"\n"+
                    "email='" + user.getEmail() + '\'' +"\n"+
                    "power=" + user.getPower() +"\n"+
                    "numberOfUsedHoliday=" + user.getNumberOfUsedHoliday() +"\n"+
                    "numberOfRestHoliday=" + user.getNumberOfRestHoliday() +"\n"+
                    "numberOfSickDay=" + user.getNumberOfSickDay() +"\n"+
                    '}';
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return accountData;
    }

    @GetMapping("/getAllMyWorktime/{employeeNummer}")
    public Iterable<Worktime> getAllMyWorktime(@PathVariable(value = "employeeNummer") long employeeNummer){

        Iterable<Worktime> worktimes = null;
        try {
            worktimes = employeeFacade.getAllMyWorktime(employeeNummer);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktimes;
    }

    @GetMapping("/getAllMyUnfinalWorktime/{employeeNummer}")
    public Iterable<Worktime> getAllMyUnfinalWorktime(@PathVariable(value = "employeeNummer") long employeeNummer){

        Iterable<Worktime> worktimes = null;
        try {
            worktimes = employeeFacade.getAllMyUnfinalWorktime(employeeNummer);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktimes;
    }

    @GetMapping("/getMyOverUndUndertime/{employeeNummer}")
    public String getMyOverUndUndertime(@PathVariable(value = "employeeNummer") long employeeNummer){

        Iterable<Worktime> finalWorktimes = null;
        Iterable<Worktime> unfinalWorktimes = null;

        float finalOvertime = 0;
        float finalUndertime = 0;
        float unfinalOvertime = 0;
        float unfinalUndertime = 0;

        try {
            finalWorktimes = employeeFacade.getAllMyFinalWorktime(employeeNummer);
            unfinalWorktimes = employeeFacade.getAllMyUnfinalWorktime(employeeNummer);

            for (Worktime worktime: finalWorktimes) {
                finalOvertime = finalOvertime + worktime.getOvertime();
                finalUndertime = finalUndertime + worktime.getUndertime();
            }
            for (Worktime worktime: unfinalWorktimes) {
                unfinalOvertime = unfinalOvertime + worktime.getOvertime();
                unfinalUndertime = unfinalUndertime + worktime.getUndertime();
            }

        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        String  message = "My finalise Overtime are "+ finalOvertime +" und my finalise Undertime are "+finalUndertime +" \n"+
                "My finalise Overtime are "+ unfinalOvertime +" und my finalise Undertime are "+ unfinalUndertime +".";

        return message;
    }

    @GetMapping("/getMyRequestOfHolidays/{employeeNummer}")
    public Iterable<RequestOfHoliday> getMyRequestOfHolidays(@PathVariable(value = "employeeNummer") long employeeNummer){

        Iterable<RequestOfHoliday> requestOfHolidays = null;
        try {
            requestOfHolidays = employeeFacade.getMyRequestOfHolidays(employeeNummer);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return requestOfHolidays;
    }

    @GetMapping("/getMyRestHolidays/{id}")
    public String getMyRestHolidays(@PathVariable(value = "id") Long id){

        User user = null;
        try {
            user = employeeFacade.getUser(id);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        assert user != null;
        return "" + user.getNumberOfRestHoliday();
    }

    //Post

    @PostMapping("/UnfinalWorktime")
    public Worktime createUnfinalWorktime(@Valid @RequestBody Worktime worktime){

        Worktime worktime1 = null;

        try {

            worktime1 = employeeFacade.createUnfinalWorktime(worktime);
        } catch (DuplicateException e) {
            e.printStackTrace();
        }

        return worktime1;
    }

    @PostMapping("/RequestOFHoliday")
    public RequestOfHoliday createRequestOfHoliday(@Valid @RequestBody RequestOfHoliday requestOfHoliday){

        RequestOfHoliday requestOfHoliday1 = null;

        try {

            requestOfHoliday1 = employeeFacade.createRequestOfHolidays(requestOfHoliday);
        } catch (DuplicateException e) {
            e.printStackTrace();
        }

        return requestOfHoliday1;
    }





}
