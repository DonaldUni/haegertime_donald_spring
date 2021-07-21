package haegerConsulting.Haegertime_SpringBoot.facade;

import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.model.*;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.RequestStatus;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.WorktimeType;
import haegerConsulting.Haegertime_SpringBoot.services.CustomerService;
import haegerConsulting.Haegertime_SpringBoot.services.ProjectService;
import haegerConsulting.Haegertime_SpringBoot.services.RequestOfHolidaysService;
import haegerConsulting.Haegertime_SpringBoot.services.UserService;
import haegerConsulting.Haegertime_SpringBoot.services.WorktimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookkeeperFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WorktimeService worktimeService;

    @Autowired
    private RequestOfHolidaysService requestOfHolidaysService;

    @Autowired
    private ProjectService projectService;



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

    public void updateUser(User newUser) throws ElementNotFoundException{

        User user = userService.getUser(newUser.getId());

        userService.saveUser(user);
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

    public String getOverAndUndertimeForEachEmployee(){

        String message = "";

        Iterable<Worktime> iterable_worktimes = worktimeService.getAllWorktime();
        ArrayList<Worktime> arrayList_worktimes = new ArrayList<>();
        iterable_worktimes.forEach(arrayList_worktimes::add);

        Iterable<User> users = userService.getAllUser();
        float overtime = 0;
        float undertime = 0;

        for (User user: users) {

            String tmp;
            for (Worktime worktime: arrayList_worktimes) {

                if (user.getId() == worktime.getUser().getId()){

                    overtime = overtime + worktime.getOvertime();
                    undertime = undertime + worktime.getUndertime();

                    arrayList_worktimes.remove(worktime); // Damit die List kleiner wird und einfacher zu bearbeiten wird
                }
            }

            tmp = "The User with the username"+ user.getUserName()+" has "+overtime + " overtime and "+ undertime +" undertime. \n";
            message = message + tmp;
        }

        return message;
    }

    public String getFinalAndUnfinalWorktimeForEachEmployee(){

        String message = "";

        Iterable<Worktime> iterable_worktimes = worktimeService.getAllWorktime();
        ArrayList<Worktime> arrayList_worktimes = new ArrayList<>();
        iterable_worktimes.forEach(arrayList_worktimes::add);

        Iterable<User> users = userService.getAllUser();
        float finalWorktime = 0;
        float finalOvertime = 0;
        float finalUndertime = 0;
        float unfinalWorktime = 0;
        float unfinalOvertime = 0;
        float unfinalUndertime = 0;


        for (User user: users) {

            String tmp;
            for (Worktime worktime: arrayList_worktimes) {

                if (user.getId() == worktime.getUser().getId() && worktime.getWorktimeType() == WorktimeType.Final){

                    finalWorktime = finalWorktime + worktime.getWorkhour();
                    finalOvertime = finalOvertime + worktime.getOvertime();
                    finalUndertime = finalUndertime + worktime.getUndertime();

                    arrayList_worktimes.remove(worktime); // Damit die List kleiner wird und einfacher zu bearbeiten wird
                }

                if (user.getId() == worktime.getUser().getId() && worktime.getWorktimeType() == WorktimeType.Unfinal){

                    unfinalWorktime = unfinalWorktime + worktime.getWorkhour();
                    unfinalOvertime = unfinalOvertime + worktime.getOvertime();
                    unfinalUndertime = unfinalUndertime + worktime.getUndertime();

                    arrayList_worktimes.remove(worktime); // Damit die List kleiner wird und einfacher zu bearbeiten wird
                }
            }

            tmp = "The User with the username"+ user.getUserName()+" has "+finalWorktime+ " finalworkhour "+finalOvertime
                    + " finalovertime and "+ finalUndertime +" finalundertime and " + unfinalWorktime +
                    " unfinalised workhour "+ unfinalOvertime + " unfinalsed Overtime and "+ unfinalUndertime +" unfinalised undertime. \n";
            message = message + tmp;
        }

        return message;
    }


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

    public Iterable<RequestOfHoliday> getAllRequestOfHolidays(){

        return requestOfHolidaysService.getAllRequestOfHolidays();
    }

    public void answerToRequestOfHolidays(Long request_id, RequestStatus answer) throws ElementNotFoundException{

        RequestOfHoliday requestOfHoliday = requestOfHolidaysService.getRequestOfHoliday(request_id);

        //Änderung und speicherung der Antwort
        requestOfHoliday.setStatus(answer);
        requestOfHolidaysService.updateRequestOfHoliday(requestOfHoliday);

        //Falls die Anfrage bestätigt geworden ist, wird jetzt die Anzahl von NumberOfUSedHolidays, numberOfRestHolidays des Users, der die Anfrage erstellt hast, aktualisiert
        if (answer == RequestStatus.Confirmed) {

            Long existing_userId = requestOfHoliday.getUser().getId();
            User user = userService.getUser(existing_userId);

            float currentNumberOfUsedHolidays = user.getNumberOfUsedHoliday() + requestOfHoliday.getNumberOfRequestedDay();
            float currentNumberOfRestHolidays = user.getNumberOfRestHoliday() - requestOfHoliday.getNumberOfRequestedDay();

            user.setNumberOfUsedHoliday(currentNumberOfUsedHolidays);
            user.setNumberOfRestHoliday(currentNumberOfRestHolidays);

            updateUser(user);
        }
    }


    //Methoden über Customer
    public Customer createCustomer(Customer customer) throws DuplicateException {

        return customerService.create(customer);
    }

    public void updateCustomer(Customer customer) throws ElementNotFoundException {

        customerService.updateCustomer(customer);
    }

    public void allocateProjectsToCustomer(List<Project> projects, Long customer_id) throws ElementNotFoundException {

        Customer customer = customerService.getCustomer(customer_id);
        List<Project> projects1 = customer.getProjects();

        projects.forEach( project -> {
            if (project!= null){

                projects1.add(project);
            }
        });

        customer.setProjects(projects1);

        customerService.updateCustomer(customer);    // Cascade Attribut testen
    }


    //Methoden über Projekt
//    public Iterable<Project> getMyProjects(Long user_id) throws ElementNotFoundException {
//
//        return projectService.getProjectsByUserId(user_id);
//    }

    public Project createProject(Project project) throws DuplicateException {

        return projectService.create(project);
    }

    public void updateProject(Project project) throws ElementNotFoundException {

        projectService.updateProject(project);
    }

    public void allocateEmployeesToProject(List<User> employees, Long project_id) throws ElementNotFoundException {

        Project project = projectService.getProject(project_id);
        List<User> employees1 = project.getUsers();

        employees.forEach( emp -> {
            if (emp!= null && emp.getPower() == Power.Employee){

                employees1.add(emp);
            }
        });

        project.setUsers(employees1);

        projectService.updateProject(project);    // Cascade Attribut testen
    }
}
