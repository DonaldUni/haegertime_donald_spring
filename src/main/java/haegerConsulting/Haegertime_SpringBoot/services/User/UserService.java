package haegerConsulting.Haegertime_SpringBoot.services.User;

import haegerConsulting.Haegertime_SpringBoot.exceptions.ProjectExceptions.ProjectNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.RequestOfHolidaysExceptions.DuplicateRequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.exceptions.RequestOfHolidaysExceptions.RequestOfHolidayNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions.UserNotFoundExceptions;
import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.DuplicateWorktimeException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.WorktimeNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.Project;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.repository.UserRepository;
import haegerConsulting.Haegertime_SpringBoot.services.CustomerService;
import haegerConsulting.Haegertime_SpringBoot.services.ProjectService;
import haegerConsulting.Haegertime_SpringBoot.services.RequestOfHolidaysService;
import haegerConsulting.Haegertime_SpringBoot.services.WorktimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorktimeService worktimeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    RequestOfHolidaysService requestOfHolidaysService;



    //Methoden über User Account
    public Iterable<User> getAllUser(){

        return userRepository.findAll();
    }

    public User getMyAccountData(long id) throws UserNotFoundExceptions{

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){

            return user.get();
        }else{

            throw new UserNotFoundExceptions();
        }
    }

    public void updateMyAccountData(long id, String oldPassword, String newPassword) throws UserNotFoundExceptions{

        Optional<User> optional_user = userRepository.findById(id);

        if (optional_user.isPresent()){

              User user = optional_user.get();
              if (user.getPassword().equals(oldPassword)){

                  user.setPassword(newPassword);
                  userRepository.save(user);
              }
        }else{

            throw new UserNotFoundExceptions();
        }
    }


    // Methoden über Worktimes
    public Worktime createUnfinalWorktime(Worktime unfinalWorktime) throws DuplicateWorktimeException{

        return worktimeService.create(unfinalWorktime);
    }

    public void updateUnfinalWorktime(Worktime unfinalWorktime) throws WorktimeNotFoundException {

         worktimeService.updateUnfinalWorktime(unfinalWorktime);
    }

    public Iterable<Worktime> getAllMyWorktime(Long user_id) throws WorktimeNotFoundException {

        return worktimeService.getAllMyWorktime(user_id);
    }

    public Iterable<Worktime> getAllMyUnfinalWorktime(Long user_id) throws WorktimeNotFoundException {

        return worktimeService.getAllMyUnfinalWorktime(user_id);
    }

    public Iterable<Worktime> getAllMyFinalWorktime(Long user_id) throws WorktimeNotFoundException {

        return worktimeService.getAllMyFinalWorktime(user_id);
    }

    public Iterable<Worktime> getMyOverUndUndertime(Long user_id) throws WorktimeNotFoundException {

        return worktimeService.getAllMyWorktime(user_id);  // Ab hier der Frontend wird die List von Alle Wortime holen und dann nur die Over- und Undertime anzeigen
    }

    public void finaliseWorktime(Long worktime_id) throws WorktimeNotFoundException {

        worktimeService.finaliseUnfinalWorktime(worktime_id);
    }


    //Methoden über Projects
    public Iterable<Project> getMyProjects(Long user_id) throws ProjectNotFoundException {

        return projectService.getProjectsByUserId(user_id);
    }


    //Methoden über RequestOfHolidays
    public RequestOfHoliday createRequestOfHolidays(RequestOfHoliday requestOfHoliday) throws DuplicateRequestOfHoliday {

        return requestOfHolidaysService.create(requestOfHoliday);
    }

    public Iterable<RequestOfHoliday> getMyRequestOfHolidays(Long user_id) throws RequestOfHolidayNotFoundException {

        return requestOfHolidaysService.getRequestOfHolidaysByUserId(user_id);
    }

    public Float getMyRestHolidays(Long user_id) throws  UserNotFoundExceptions{

        Optional<User> user = userRepository.findById(user_id);

        if (user.isPresent()){

            return user.get().getNumberOfRestHoliday();
        }else{

            throw new UserNotFoundExceptions();
        }
    }















}
