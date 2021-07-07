package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.DuplicateWorktimeException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.WorktimeNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.repository.FinalWorktimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinalWorktimeService {


    @Autowired
    FinalWorktimeRepository finalWorktimeRepository;



    public FinalWorktime create(FinalWorktime finalWorktime) throws DuplicateWorktimeException {

        if (finalWorktime.getId() != null && finalWorktimeRepository.existsById(finalWorktime.getId())){

            throw new DuplicateWorktimeException();
        }

        return finalWorktimeRepository.save(finalWorktime);
    }

    public FinalWorktime getFinalWorktime(Long id) throws WorktimeNotFoundException{

        Optional<FinalWorktime> finalWorktime = finalWorktimeRepository.findById(id);

        if (finalWorktime.isEmpty()){

            throw new WorktimeNotFoundException();
        }

        return finalWorktime.get();
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
