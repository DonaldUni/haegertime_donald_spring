package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.DuplicateWorktimeException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.WorktimeNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.model.UnfinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.model.builder.FinalWorktimeBuilder;
import haegerConsulting.Haegertime_SpringBoot.repository.FinalWorktimeRepository;
import haegerConsulting.Haegertime_SpringBoot.repository.UnfinalWorktimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnfinalWorktimeService {

    @Autowired
    UnfinalWorktimeRepository unfinalWorktimeRepository;

    @Autowired
    FinalWorktimeRepository finalWorktimeRepository; // zu fragen



    public UnfinalWorktime create(UnfinalWorktime unfinalWorktime) throws DuplicateWorktimeException {

        if (unfinalWorktime.getId() != null && unfinalWorktimeRepository.existsById(unfinalWorktime.getId())){

            throw new DuplicateWorktimeException();
        }

        return unfinalWorktimeRepository.save(unfinalWorktime);
    }

    public UnfinalWorktime getUnfinalWorktime(Long id) throws WorktimeNotFoundException{

        Optional<UnfinalWorktime> unfinalWorktime = unfinalWorktimeRepository.findById(id);

        if (unfinalWorktime.isEmpty()){

            throw new WorktimeNotFoundException();
        }

        return unfinalWorktime.get();
    }

    public Iterable<UnfinalWorktime> getAllMyUnfinalWorktime(Long id) throws WorktimeNotFoundException{

        Iterable<UnfinalWorktime> unfinalWorktime = unfinalWorktimeRepository.findAllByUserId(id);

        if (unfinalWorktime !=null){

            throw new WorktimeNotFoundException();
        }else{

            return unfinalWorktime;
        }
    }

    public Iterable<UnfinalWorktime> getAllUnfinalWorktime(){

        return unfinalWorktimeRepository.findAll();
    }

    public void updateUnfinalWorktime(Long id, UnfinalWorktime newUnfinalWorktime) throws WorktimeNotFoundException{

        Optional<UnfinalWorktime> optional_unfinalWorktime = unfinalWorktimeRepository.findById(id);

        if (optional_unfinalWorktime.isPresent()){

            unfinalWorktimeRepository.save(newUnfinalWorktime);
        }else{

            throw new WorktimeNotFoundException();
        }
    }

    public void finaliseUnfinalWorktime(Long id) throws WorktimeNotFoundException{

        if (id != null && unfinalWorktimeRepository.existsById(id)){

            UnfinalWorktime unfinalWorktime = unfinalWorktimeRepository.getById(id);
            finalWorktimeRepository.save(convertToFinal(unfinalWorktime));
        }else {

            throw new WorktimeNotFoundException();
        }
    }

    public void deleteById(Long id) throws WorktimeNotFoundException {

        if (unfinalWorktimeRepository.existsById(id)){

            unfinalWorktimeRepository.deleteById(id);
        }else {

            throw new WorktimeNotFoundException();
        }
    }

    public void deleteAll() {

        unfinalWorktimeRepository.deleteAll();
    }

    private FinalWorktime convertToFinal(UnfinalWorktime unfinalWorktime){

        return new FinalWorktimeBuilder().id(unfinalWorktime.getId())
                                        .project(unfinalWorktime.getProject())
                                        .user(unfinalWorktime.getUser())
                                        .workhour(unfinalWorktime.getWorkhour())
                                        .overtime(unfinalWorktime.getOvertime())
                                        .undertime(unfinalWorktime.getUndertime())
                                        .period(unfinalWorktime.getPeriod())
                                        .build();
    }
}
