package haegerConsulting.Haegertime_SpringBoot.controller;


import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UsernameEmptyException;
import haegerConsulting.Haegertime_SpringBoot.facade.AdminFacade;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Stack;

@RestController
@RequestMapping("/API/Haegertime/admins")
public class AdminController {

    @Autowired
    private AdminFacade adminFacade;


    //Get
    //spezialisierten Get-Methoden

    //Post
    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody User user){

        User savedUser = null;
        try {
            savedUser = adminFacade.createUser(user);
        } catch (UsernameEmptyException | DuplicateException e) {
            e.printStackTrace();
        }

        return savedUser;
    }
//    {
//        "lastname": "lastname",
//            "firstname":"firstname",
//            "employeeNummer": 10,
//            "userName": "userName",
//            "password": "password04",
//            "email": "lastname_firstname@gmail.com",
//            "power": "Employee",
//            "status": "actived",
//            "numberOfUsedHoliday": 10,
//            "numberOfRestHoliday":20,
//            "numberOfSickDay": 1,
//            "NUMBEROFHOLIDAY":30
//    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        User updatedUser = null;
        try {
            updatedUser = adminFacade.updateUser(user);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return updatedUser;
    }

    @PutMapping("/updateUsernameOfUser/{user_id}")
    public User updateUsernameOfUser(@PathVariable(value = "user_id") long user_id,@RequestParam String username){
        User user = null;
        User updatedUser = null;
        try {
            user = adminFacade.getUser(user_id);
            user.setUserName(username);
            updatedUser = adminFacade.updateUser(user);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return updatedUser;
    }

    @PutMapping("/updatePower/{user_id}")
    public User updatePowerOfUser(@PathVariable(value = "user_id") long user_id, @RequestParam Power power){
        User user = null;
        User updatedUser = null;
        try {
            user = adminFacade.getUser(user_id);
            user.setPower(power);
            updatedUser = adminFacade.updateUser(user);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return updatedUser;
    }

    @PutMapping("/updateStatus/{user_id}")
    public User updateStatusOfUser(@PathVariable(value = "user_id") long user_id, @RequestParam Status status){
        User user = null;
        User updatedUser = null;
        try {
            user = adminFacade.getUser(user_id);
            user.setStatus(status);
            updatedUser = adminFacade.updateUser(user);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

        return updatedUser;
    }

    //delete
    @DeleteMapping("/deletes/user/{id}")
    public void deleteUserById(@PathVariable(value = "id") Long userId){

        try {
            adminFacade.deleteUserById(userId);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/deletes/user")
    public void deleteUserByUsername(@RequestParam String username){

        try {
            adminFacade.deleteUserByUsername(username);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/deletes")
    public void deleteUserById(){

        adminFacade.deleteAllUser();
    }


}
