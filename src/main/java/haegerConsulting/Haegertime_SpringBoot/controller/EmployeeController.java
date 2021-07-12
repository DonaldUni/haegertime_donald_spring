package haegerConsulting.Haegertime_SpringBoot.controller;

import haegerConsulting.Haegertime_SpringBoot.facade.AdminFacade;
import haegerConsulting.Haegertime_SpringBoot.facade.EmployeeFacade;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/API/Haegertime/users")
public class EmployeeController {

    @Autowired
    private EmployeeFacade employeeFacade;


    @GetMapping
    public Iterable<User> getAllUser(){

        return employeeFacade.getAllUser();
    }


}
