package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.*;
import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.repository.FinalWorktimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FinalWorktimeService {


    @Autowired
    FinalWorktimeRepository finalWorktimeRepository;



    public FinalWorktime create(FinalWorktime finalWorktime) throws DuplicateWorktimeException{

        if (finalWorktime.getId() != null && finalWorktimeRepository.existsById(finalWorktime.getId())){

            throw new DuplicateWorktimeException();
        }

        return finalWorktimeRepository.save(finalWorktime);
    }

    public Iterable<FinalWorktime> getAllFinalWorktime(){

        return finalWorktimeRepository.findAll();
    }



    public void deleteById(Long id) throws WorktimeNotFoundException {

        if (finalWorktimeRepository.existsById(id)){

            finalWorktimeRepository.deleteById(id);
        }else {

            throw new WorktimeNotFoundException();
        }
    }

    public void deleteAll() {

        finalWorktimeRepository.deleteAll();
    }

}
