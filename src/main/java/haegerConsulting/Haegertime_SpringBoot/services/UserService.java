package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UsernameEmptyException;
import haegerConsulting.Haegertime_SpringBoot.model.*;
import haegerConsulting.Haegertime_SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private WorktimeService worktimeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RequestOfHolidaysService requestOfHolidaysService;



    //Methoden über User Account
    public boolean existsById(long id){

        return userRepository.existsById(id);
    }

    public boolean existsByUsername(String username){

        return userRepository.existsByUserName(username);
    }

    public User create(User user) throws DuplicateException, UsernameEmptyException {



        if (user.getId() != null) {
            if (userRepository.existsById(user.getId())) {
                throw new DuplicateException("This element already exist.");
            }
        }

        if (user.getUserName() == null){

            throw new UsernameEmptyException();
        }

        return userRepository.save(user);
    }


    public User saveUser(User user){

        return userRepository.save(user);
    }

    public User getUser(long id) throws ElementNotFoundException {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){

            return user.get();
        }else{

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public User getUserByEmployeeNummer(long employeeNummer) throws ElementNotFoundException {

        Optional<User> user = userRepository.findByEmployeeNummer(employeeNummer);

        if (user.isPresent()){

            return user.get();
        }else{

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteById(Long id) throws ElementNotFoundException {

        if (userRepository.existsById(id)){

            userRepository.deleteById(id);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteByUsername(String username) throws ElementNotFoundException {

        if (userRepository.existsByUserName(username)){

            userRepository.deleteByUserName(username);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteAll(){

        userRepository.deleteAll();
    }

    public Iterable<User> getAllUser(){

        return userRepository.findAll();
    }

    public User getMyAccountData(long id) throws ElementNotFoundException {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){

            return user.get();
        }else{

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void updateMyAccountData(long id, String oldPassword, String newPassword) throws ElementNotFoundException{

        Optional<User> optional_user = userRepository.findById(id);

        if (optional_user.isPresent()){

              User user = optional_user.get();
              if (user.getPassword().equals(oldPassword)){

                  user.setPassword(newPassword);
                  userRepository.save(user);
              }
        }else{

            throw new ElementNotFoundException("This element has been not found.");
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

        return requestOfHolidaysService.getRequestOfHolidaysByEmployeeNummer(user_id);
    }

    public Float getMyRestHolidays(Long user_id) throws  ElementNotFoundException{

        Optional<User> user = userRepository.findById(user_id);

        if (user.isPresent()){

            return user.get().getNumberOfRestHoliday();
        }else{

            throw new ElementNotFoundException("This element has been not found.");
        }
    }















}
