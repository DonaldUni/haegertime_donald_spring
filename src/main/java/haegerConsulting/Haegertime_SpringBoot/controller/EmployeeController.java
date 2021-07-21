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
import java.util.List;

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
                    "Id=" + user.getId() +"\n"+
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

    @GetMapping("/getAllWorktimes")
    public Iterable<Worktime> getAllWorktimes(){

        return employeeFacade.getAllWorktime();
    }

    @GetMapping("/getAllWorktimes/get/{id}")
    public Worktime getWorktime(@PathVariable(value = "id") Long id){

        Worktime worktime = null;

        try {
            worktime = employeeFacade.getWorktimeById(id);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktime;
    }

    @GetMapping("/getAllMyWorktime/{userId}")
    public Iterable<Worktime> getAllMyWorktime(@PathVariable(value = "userId") Long userId){

        Iterable<Worktime> worktimes = null;
        try {
            worktimes = employeeFacade.getAllMyWorktime(userId);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktimes;
    }

    @GetMapping("/getAllMyWorktimeByProjectID/{id}")
    public List<Worktime> getAllMyWorktimeByProjectID(@PathVariable(value = "id") Long id){

        List<Worktime> worktimes = null;
        try {
            worktimes = employeeFacade.getAllMyWorktimeByProjectID(id);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktimes;
    }

    @GetMapping("/getAllMyUnfinalWorktime/{userId}")
    public Iterable<Worktime> getAllMyUnfinalWorktime(@PathVariable(value = "userId") Long userId){

        Iterable<Worktime> worktimes = null;
        try {
            worktimes = employeeFacade.getAllMyUnfinalWorktime(userId);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktimes;
    }

    @GetMapping("/getAllMyFinalWorktime/{userId}")
    public Iterable<Worktime> getAllMyFinalWorktime(@PathVariable(value = "userId") Long userId){

        Iterable<Worktime> worktimes = null;
        try {
            worktimes = employeeFacade.getAllMyFinalWorktime(userId);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktimes;
    }

    @GetMapping("/getMyOverUndUndertime/{userId}")
    public String getMyOverUndUndertime(@PathVariable(value = "userId") Long userId){

        Iterable<Worktime> finalWorktimes = null;
        Iterable<Worktime> unfinalWorktimes = null;

        float finalOvertime = 0;
        float finalUndertime = 0;
        float unfinalOvertime = 0;
        float unfinalUndertime = 0;

        try {
            finalWorktimes = employeeFacade.getAllMyFinalWorktime(userId);
            unfinalWorktimes = employeeFacade.getAllMyUnfinalWorktime(userId);

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

        return "My finalise Overtime are "+ finalOvertime  +" und my finalise Undertime are "+finalUndertime +" \n"+
                "My unfinalise Overtime are "+ unfinalOvertime +" und my unfinalise Undertime are "+ unfinalUndertime +".";
    }

    @GetMapping("/getMyRequestOfHolidays/{userId}")
    public Iterable<RequestOfHoliday> getMyRequestOfHolidays(@PathVariable(value = "userId") Long userId){

        Iterable<RequestOfHoliday> requestOfHolidays = null;
        try {
            requestOfHolidays = employeeFacade.getMyRequestOfHolidays(userId);
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


    //Put
    @PutMapping("/updateMyAccountData")
    public String updateMyAccountData(@RequestParam Long userId, @RequestParam String oldPassword,@RequestParam String newPassword ){

        User user1 = null;
        try {

            user1 = employeeFacade.updateMyAccountData(userId, oldPassword, newPassword);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        assert user1 != null;
        return  "My Account { \n" +
                "Id = " + user1.getId() +"\n"+
                "password = " + user1.getPassword() +"\n";
    }

    @PutMapping("/updateUnfinalWorktime")
    public Worktime updateUnfinalWorktime(@RequestBody Worktime worktime ){

        Worktime worktime1 = null;
        try {
            worktime1 = employeeFacade.updateUnfinalWorktime(worktime);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktime;
    }

    @PutMapping("/finaliseAllMyUnfinalWorktime/{userId}")
    public Iterable<Worktime> finaliseAllMyUnfinalWorktime(@PathVariable(value = "userId") Long userId ){

        Iterable<Worktime> worktimes = null;
        try {
            worktimes = employeeFacade.finaliseAllMyUnfinalWorktime(userId);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return worktimes;
    }





}
