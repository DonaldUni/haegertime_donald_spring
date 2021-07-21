package haegerConsulting.Haegertime_SpringBoot.facade;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UsernameEmptyException;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Status;
import haegerConsulting.Haegertime_SpringBoot.services.ProjectService;
import haegerConsulting.Haegertime_SpringBoot.services.RequestOfHolidaysService;
import haegerConsulting.Haegertime_SpringBoot.services.UserService;
import haegerConsulting.Haegertime_SpringBoot.services.WorktimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminFacade {


    @Autowired
    private UserService userService;

    @Autowired
    private WorktimeService worktimeService;

    @Autowired
    private RequestOfHolidaysService requestOfHolidaysService;

    @Autowired
    private ProjectService projectService;


    public User getUser(long id) throws ElementNotFoundException {

        return userService.getUser(id);
    }

    public User createUser(User user) throws UsernameEmptyException, DuplicateException {

        if (user.getId() != null && userService.existsById(user.getId())){

            throw new DuplicateException("This element already exist.");
        }
        if (user.getUserName().isEmpty()){

            throw new UsernameEmptyException();
        }

        return userService.saveUser(user);

    }

    public User updateUser(User newUser) throws ElementNotFoundException {

        return userService.saveUser(newUser);
    }

    public void updateUsernameOfUser(Long user_id, String newUsername) throws ElementNotFoundException{

        User user = userService.getUser(user_id);

        user.setUserName(newUsername);
        userService.saveUser(user);

    }

    public void updatePowerOfUser(Long user_id, Power newPower) throws ElementNotFoundException{

        User user = userService.getUser(user_id);

        user.setPower(newPower);
        userService.saveUser(user);
    }

    public void updateStatusOfUser(Long user_id, Status newStatus) throws ElementNotFoundException{

        User user = userService.getUser(user_id);

        user.setStatus(newStatus);
        userService.saveUser(user);
    }

    public void deleteUserById(Long id) throws ElementNotFoundException {

        if (userService.existsById(id)){

            userService.deleteById(id);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteUserByUsername(String username) throws ElementNotFoundException{

        if (userService.existsByUsername(username)){

            userService.deleteByUsername(username);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteAllUser() {

        userService.deleteAll();
    }

    //Methode für alle Employee
    //Methoden über User
    public Iterable<User> getAllUser(){

        return userService.getAllUser();
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
