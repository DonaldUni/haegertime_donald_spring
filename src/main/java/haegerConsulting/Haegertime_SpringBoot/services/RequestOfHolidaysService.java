package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.RequestOfHolidaysExceptions.DuplicateRequestOfHoliday;
import haegerConsulting.Haegertime_SpringBoot.exceptions.RequestOfHolidaysExceptions.RequestOfHolidayNotFoundException;
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



    public RequestOfHoliday create(RequestOfHoliday requestOfHoliday) throws DuplicateRequestOfHoliday {

        if (requestOfHoliday.getId() != null && requestOfHolidaysRepository.existsById(requestOfHoliday.getId())){

            throw new DuplicateRequestOfHoliday();
        }

        return requestOfHolidaysRepository.save(requestOfHoliday);
    }

    public void updateRequestOfHoliday(RequestOfHoliday newRequest) throws RequestOfHolidayNotFoundException {

        Optional<RequestOfHoliday> optionalRequestOfHoliday = requestOfHolidaysRepository.findById(newRequest.getId());

        if (optionalRequestOfHoliday.isPresent()){

            requestOfHolidaysRepository.save(newRequest);
        }else {

            throw new RequestOfHolidayNotFoundException();
        }
    }

    public Iterable<RequestOfHoliday> getRequestOfHolidayByUserId(Long user_id) throws RequestOfHolidayNotFoundException{

        Iterable<RequestOfHoliday> requestOfHolidays = requestOfHolidaysRepository.findAllByUserId(user_id);

        if (requestOfHolidays == null){

            throw new RequestOfHolidayNotFoundException();
        }else {

            return requestOfHolidays;
        }
    }

    public List<RequestOfHoliday> getAllRequestOfHolidays(){

        return requestOfHolidaysRepository.findAll();
    }

    public void deleteById(Long id) throws RequestOfHolidayNotFoundException {

        if (requestOfHolidaysRepository.existsById(id)){

            requestOfHolidaysRepository.deleteById(id);
        }else {

            throw new RequestOfHolidayNotFoundException();
        }
    }

    public void deleteAll(){

        requestOfHolidaysRepository.deleteAll();
    }
}
