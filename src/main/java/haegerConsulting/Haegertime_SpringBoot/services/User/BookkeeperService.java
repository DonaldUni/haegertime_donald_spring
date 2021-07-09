package haegerConsulting.Haegertime_SpringBoot.services.User;

import haegerConsulting.Haegertime_SpringBoot.exceptions.Customer.CustomerNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.Customer.DuplicateCustomerException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ProjectExceptions.DuplicateProjectException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ProjectExceptions.ProjectNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.RequestOfHolidaysExceptions.RequestOfHolidayNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions.UserNotFoundExceptions;
import haegerConsulting.Haegertime_SpringBoot.model.*;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.Power;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.RequestStatus;
import haegerConsulting.Haegertime_SpringBoot.services.CustomerService;
import haegerConsulting.Haegertime_SpringBoot.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookkeeperService extends UserService {

    @Autowired
    CustomerService customerService;




    //Methoden über User
    public void updateUser(User newUser) throws UserNotFoundExceptions{

        Optional<User> optional_user = userRepository.findById(newUser.getId());

        if (optional_user.isPresent()){

            userRepository.save(newUser);
        }else{

            throw new UserNotFoundExceptions();
        }
    }

//    public String getOverAndUndertimeForEachEmployee(){
//
//        Iterable<Worktime> iterable_worktimes = worktimeService.getAllWorktime();
//
////        ArrayList<Worktime> arrayList_worktimes = new ArrayList<>();
////
////        iterable_worktimes.forEach(arrayList_worktimes::add);
//
//        // 1erste Methode
//        Iterable<User> users = getAllUser();
//        float overtime;
//        float undertime;
//
//        for (User user: users) {
//            for (Worktime worktime: iterable_worktimes) {
//
//
//            }
//
//        }
//
//
//    }


    //Methoden über RequestOfHolidays
    public Iterable<RequestOfHoliday> getAllRequestOfHolidays(){

        return requestOfHolidaysService.getAllRequestOfHolidays();
    }

    public void answerToRequestOfHolidays(Long request_id, RequestStatus answer) throws RequestOfHolidayNotFoundException, UserNotFoundExceptions {

        RequestOfHoliday requestOfHoliday = requestOfHolidaysService.getRequestOfHoliday(request_id);

        //Änderung und speicherung der Antwort
        requestOfHoliday.setStatus(answer);
        requestOfHolidaysService.updateRequestOfHoliday(requestOfHoliday);

        //Falls die Anfrage bestätigt geworden ist, wird jetzt die Anzahl von NumberOfUSedHolidays, numberOfRestHolidays des Users, der die Anfrage erstellt hast, aktualisiert
        if (answer == RequestStatus.Confirmed) {

            Long existing_userId = requestOfHoliday.getUser().getId();
            User user = userRepository.getById(existing_userId);

            float currentNumberOfUsedHolidays = user.getNumberOfUsedHoliday() + requestOfHoliday.getNumberOfRequestedDay();
            float currentNumberOfRestHolidays = user.getNumberOfRestHoliday() - requestOfHoliday.getNumberOfRequestedDay();

            user.setNumberOfUsedHoliday(currentNumberOfUsedHolidays);
            user.setNumberOfRestHoliday(currentNumberOfRestHolidays);

            updateUser(user);
        }
    }


    //Methoden über Customer
    public Customer createCustomer(Customer customer) throws DuplicateCustomerException {

        return customerService.create(customer);
    }

    public void updateCustomer(Customer customer) throws CustomerNotFoundException {

        customerService.updateCustomer(customer);
    }

    public void allocateProjectsToCustomer(List<Project> projects, Long customer_id) throws CustomerNotFoundException {

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
    public Project createProject(Project project) throws DuplicateProjectException {

        return projectService.create(project);
    }

    public void updateCustomer(Project project) throws ProjectNotFoundException {

        projectService.updateProject(project);
    }

    public void allocateEmployeesToProject(List<User> employees, Long project_id) throws ProjectNotFoundException {

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
