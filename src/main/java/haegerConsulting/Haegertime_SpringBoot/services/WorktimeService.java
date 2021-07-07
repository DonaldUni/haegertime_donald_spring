package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.DuplicateWorktimeException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.WorktimeNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.WorktimeType;
import haegerConsulting.Haegertime_SpringBoot.repository.WorktimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorktimeService {

    @Autowired
    WorktimeRepository worktimeRepository;



    public Worktime create(Worktime worktime) throws DuplicateWorktimeException {

        if (worktime.getId() != null && worktimeRepository.existsById(worktime.getId())){

            throw new DuplicateWorktimeException();
        }

        return worktimeRepository.save(worktime);
    }

    public Worktime getUnfinalWorktime(Long id) throws WorktimeNotFoundException{

        Optional<Worktime> unfinalWorktime = worktimeRepository.finbByIdAndWorktimeType(id, WorktimeType.Unfinal);

        if (unfinalWorktime.isEmpty()){

            throw new WorktimeNotFoundException();
        }

        return unfinalWorktime.get();
    }

    public Worktime getFinalWorktime(Long id) throws WorktimeNotFoundException{

        Optional<Worktime> finalWorktime = worktimeRepository.finbByIdAndWorktimeType(id, WorktimeType.Final);

        if (finalWorktime.isEmpty()){

            throw new WorktimeNotFoundException();
        }

        return finalWorktime.get();
    }

    public Iterable<Worktime> getAllMyUnfinalWorktime(Long id) throws WorktimeNotFoundException{

        Iterable<Worktime> unfinalWorktime = worktimeRepository.findAllByUserIdAndWortimeType(id, WorktimeType.Unfinal);

        if (unfinalWorktime !=null){

            throw new WorktimeNotFoundException();
        }else{

            return unfinalWorktime;
        }
    }

    public Iterable<Worktime> getAllMyFinalWorktime(Long id) throws WorktimeNotFoundException{

        Iterable<Worktime> finalWorktime = worktimeRepository.findAllByUserIdAndWortimeType(id, WorktimeType.Final);

        if (finalWorktime !=null){

            throw new WorktimeNotFoundException();
        }else{

            return finalWorktime;
        }
    }

    public Iterable<Worktime> getAllMyWorktime(Long id) throws WorktimeNotFoundException{

        Iterable<Worktime> finalWorktime = worktimeRepository.findAllByUserId(id);

        if (finalWorktime !=null){

            throw new WorktimeNotFoundException();
        }else{

            return finalWorktime;
        }
    }

    public Iterable<Worktime> getAllUnfinalWorktime(){

        return worktimeRepository.findAllByWorktimeType(WorktimeType.Unfinal);
    }

    public Iterable<Worktime> getAllFinalWorktime(){

        return worktimeRepository.findAllByWorktimeType(WorktimeType.Final);
    }

    public void updateUnfinalWorktime(Worktime newWorktime) throws WorktimeNotFoundException{

        Optional<Worktime> optional_unfinalWorktime = worktimeRepository.findById(newWorktime.getId());

        if (optional_unfinalWorktime.isPresent()){

            worktimeRepository.save(newWorktime);
        }else{

            throw new WorktimeNotFoundException();
        }
    }

    public void finaliseUnfinalWorktime(Long id) throws WorktimeNotFoundException{

        if (id != null && worktimeRepository.existsById(id)){

            Worktime worktime = worktimeRepository.getById(id);
            worktime.setWorktimeType(WorktimeType.Final);

            worktimeRepository.save(worktime);
        }else {

            throw new WorktimeNotFoundException();
        }
    }

    public void deleteById(Long id) throws WorktimeNotFoundException {

        if (worktimeRepository.existsById(id)){

            worktimeRepository.deleteById(id);
        }else {

            throw new WorktimeNotFoundException();
        }
    }

    public void deleteAllUnfinalWorktime() {

        worktimeRepository.deleteAllByWorktime(WorktimeType.Unfinal);
    }

    public void deleteAllFinalWorktime() {

        worktimeRepository.deleteAllByWorktime(WorktimeType.Final);
    }

    public void deleteAll() {

        worktimeRepository.deleteAll();
    }

}
