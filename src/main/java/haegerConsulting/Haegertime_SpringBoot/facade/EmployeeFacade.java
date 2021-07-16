package haegerConsulting.Haegertime_SpringBoot.facade;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.Project;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class EmployeeFacade {

    @Autowired
    UserService userService;

    @Autowired
    WorktimeService worktimeService;

    @Autowired
    RequestOfHolidaysService requestOfHolidaysService;

    @Autowired
    ProjectService projectService;


    //Methoden über User
    public Iterable<User> getAllUser(){

        return userService.getAllUser();
    }

    public User getUser(long id) throws ElementNotFoundException {

        return userService.getUser(id);
    }

    public User getMyAccountData(long id) throws ElementNotFoundException {

        return userService.getUser(id);
    }

    public void updateMyAccountData(long id, String oldPassword, String newPassword) throws ElementNotFoundException{

        User user = userService.getUser(id);

        if (user.getPassword().equals(oldPassword)){

            user.setPassword(newPassword);
            userService.saveUser(user);
        }
    }


    // Methoden über Worktimes
    public Worktime createUnfinalWorktime(Worktime unfinalWorktime) throws DuplicateException {

        return worktimeService.create(unfinalWorktime);
    }

    public void updateUnfinalWorktime(Worktime unfinalWorktime) throws ElementNotFoundException {

        worktimeService.updateUnfinalWorktime(unfinalWorktime);
    }

    public Iterable<Worktime> getAllMyWorktime(Long user_id) throws ElementNotFoundException {

        return worktimeService.getAllMyWorktime(user_id);
    }

    public Iterable<Worktime> getAllMyUnfinalWorktime(Long user_id) throws ElementNotFoundException {

        return worktimeService.getAllMyUnfinalWorktime(user_id);
    }

    public Iterable<Worktime> getAllMyFinalWorktime(Long user_id) throws ElementNotFoundException {

        return worktimeService.getAllMyFinalWorktime(user_id);
    }

    public Iterable<Worktime> getMyOverUndUndertime(Long user_id) throws ElementNotFoundException {

        return worktimeService.getAllMyWorktime(user_id);  // Ab hier der Frontend wird die List von Alle Wortime holen und dann nur die Over- und Undertime anzeigen
    }

    public void finaliseWorktime(Long worktime_id) throws ElementNotFoundException {

        worktimeService.finaliseUnfinalWorktime(worktime_id);
    }


    //Methoden über Projects
//    public Iterable<Project> getMyProjects(Long user_id) throws ElementNotFoundException {
//
//        return projectService.getProjectsByUserId(user_id);
//    }


    //Methoden über RequestOfHolidays
    public RequestOfHoliday createRequestOfHolidays(RequestOfHoliday requestOfHoliday) throws DuplicateException {

        return requestOfHolidaysService.create(requestOfHoliday);
    }

    public Iterable<RequestOfHoliday> getMyRequestOfHolidays(Long user_id) throws ElementNotFoundException {

        return requestOfHolidaysService.getRequestOfHolidaysByUserId(user_id);
    }

    public Float getMyRestHolidays(Long user_id) throws  ElementNotFoundException{

        User user = userService.getUser(user_id);

        return user.getNumberOfRestHoliday();
    }


}
