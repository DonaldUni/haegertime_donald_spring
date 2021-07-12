package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
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



    public Worktime create(Worktime worktime) throws DuplicateException {

        if (worktime.getId() != null && worktimeRepository.existsById(worktime.getId())){

            throw new DuplicateException("This element already exist.");
        }

        return worktimeRepository.save(worktime);
    }

    public Worktime getUnfinalWorktime(Long id) throws ElementNotFoundException {

        Optional<Worktime> unfinalWorktime = worktimeRepository.findByIdAndWorktimeType(id, WorktimeType.Unfinal);

        if (unfinalWorktime.isEmpty()){

            throw new ElementNotFoundException("This element has been not found.");
        }

        return unfinalWorktime.get();
    }

    public Worktime getFinalWorktime(Long id) throws ElementNotFoundException{

        Optional<Worktime> finalWorktime = worktimeRepository.findByIdAndWorktimeType(id, WorktimeType.Final);

        if (finalWorktime.isEmpty()){

            throw new ElementNotFoundException("This element has been not found.");
        }

        return finalWorktime.get();
    }

    public Iterable<Worktime> getAllMyUnfinalWorktime(Long id) throws ElementNotFoundException{

        Iterable<Worktime> unfinalWorktime = worktimeRepository.findAllByUserIdAndWorktimeType(id, WorktimeType.Unfinal);

        if (unfinalWorktime !=null){

            throw new ElementNotFoundException("This element has been not found.");
        }else{

            return unfinalWorktime;
        }
    }

    public Iterable<Worktime> getAllMyFinalWorktime(Long id) throws ElementNotFoundException{

        Iterable<Worktime> finalWorktime = worktimeRepository.findAllByUserIdAndWorktimeType(id, WorktimeType.Final);

        if (finalWorktime !=null){

            throw new ElementNotFoundException("This element has been not found.");
        }else{

            return finalWorktime;
        }
    }

    public Iterable<Worktime> getAllMyWorktime(Long id) throws ElementNotFoundException{

        Iterable<Worktime> finalWorktime = worktimeRepository.findAllByUserId(id);

        if (finalWorktime !=null){

            throw new ElementNotFoundException("This element has been not found.");
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

    public Iterable<Worktime> getAllWorktime(){

        return worktimeRepository.findAll();
    }

    public void updateUnfinalWorktime(Worktime newWorktime) throws ElementNotFoundException{

        Optional<Worktime> optional_unfinalWorktime = worktimeRepository.findById(newWorktime.getId());

        if (optional_unfinalWorktime.isPresent()){

            worktimeRepository.save(newWorktime);
        }else{

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void finaliseUnfinalWorktime(Long id) throws ElementNotFoundException{

        if (id != null && worktimeRepository.existsById(id)){

            Worktime worktime = worktimeRepository.getById(id);
            worktime.setWorktimeType(WorktimeType.Final);

            worktimeRepository.save(worktime);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteById(Long id) throws ElementNotFoundException {

        if (worktimeRepository.existsById(id)){

            worktimeRepository.deleteById(id);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteAllUnfinalWorktime() {

        worktimeRepository.deleteAllByWorktimeType(WorktimeType.Unfinal);
    }

    public void deleteAllFinalWorktime() {

        worktimeRepository.deleteAllByWorktimeType(WorktimeType.Final);
    }

    public void deleteAll() {

        worktimeRepository.deleteAll();
    }

}
