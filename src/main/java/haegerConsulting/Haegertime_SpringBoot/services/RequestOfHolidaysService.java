package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.repository.RequestOfHolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RequestOfHolidaysService {

    @Autowired
    RequestOfHolidaysRepository requestOfHolidaysRepository;



    public RequestOfHoliday create(RequestOfHoliday requestOfHoliday) throws DuplicateException {

        if (requestOfHoliday.getId() != null && requestOfHolidaysRepository.existsById(requestOfHoliday.getId())){

            throw new DuplicateException("This element already exist.");
        }

        return requestOfHolidaysRepository.save(requestOfHoliday);
    }

    public void updateRequestOfHoliday(RequestOfHoliday newRequest) throws ElementNotFoundException {

        Optional<RequestOfHoliday> optionalRequestOfHoliday = requestOfHolidaysRepository.findById(newRequest.getId());

        if (optionalRequestOfHoliday.isPresent()){

            requestOfHolidaysRepository.save(newRequest);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public RequestOfHoliday getRequestOfHoliday(Long id) throws ElementNotFoundException {

        Optional<RequestOfHoliday> requestOfHoliday = requestOfHolidaysRepository.findById(id);

        if (requestOfHoliday.isPresent()){

            return requestOfHoliday.get();
        }else{

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public Iterable<RequestOfHoliday> getRequestOfHolidaysByEmployeeNummer(long employeeNummer) throws ElementNotFoundException{

        Iterable<RequestOfHoliday> requestOfHolidays = requestOfHolidaysRepository.findAllByUserId(employeeNummer);

        if (requestOfHolidays == null){

            throw new ElementNotFoundException("This element has been not found.");
        }else {

            return requestOfHolidays;
        }
    }

    public List<RequestOfHoliday> getAllRequestOfHolidays(){

        return requestOfHolidaysRepository.findAll();
    }

    public void deleteById(Long id) throws ElementNotFoundException {

        if (requestOfHolidaysRepository.existsById(id)){

            requestOfHolidaysRepository.deleteById(id);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteAll(){

        requestOfHolidaysRepository.deleteAll();
    }
}
