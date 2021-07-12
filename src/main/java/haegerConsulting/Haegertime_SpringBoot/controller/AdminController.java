package haegerConsulting.Haegertime_SpringBoot.controller;


import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UsernameEmptyException;
import haegerConsulting.Haegertime_SpringBoot.facade.AdminFacade;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/Haegertime/admins")
public class AdminController {

    @Autowired
    private AdminFacade adminFacade;



//    @PostMapping("/user")
//    public User createUser(@RequestBody User user){
//
//        User user1 = null;
//        try {
//
//            user1 =  adminFacade.create(user);
//
//        } catch (DuplicateException | UsernameEmptyException e) {
//            e.printStackTrace();
//        }
//
//        return user1;
//    }

    @GetMapping
    public Iterable<User> getAllUser(){

        return adminFacade.getAllUser();
    }
}
