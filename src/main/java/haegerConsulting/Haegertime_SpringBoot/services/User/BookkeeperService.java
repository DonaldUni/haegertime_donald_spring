package haegerConsulting.Haegertime_SpringBoot.services.User;

import haegerConsulting.Haegertime_SpringBoot.exceptions.RequestOfHolidaysExceptions.RequestOfHolidayNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.UserExceptions.UserNotFoundExceptions;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.RequestStatus;
import haegerConsulting.Haegertime_SpringBoot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookkeeperService extends UserService{

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
}
