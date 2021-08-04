package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.WorktimeType;
import haegerConsulting.Haegertime_SpringBoot.repository.WorktimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorktimeService {

    @Autowired
    WorktimeRepository worktimeRepository;



    public Worktime create(Worktime worktime) throws DuplicateException {

        if (worktime.getId() != null && worktimeRepository.existsById(worktime.getId())){

            throw new DuplicateException("This element already exist.");
        }

        Worktime worktime1 = worktimeRepository.save(worktime);
        return worktime1;
    }

    public Iterable<Worktime> getAllMyUnfinalWorktime(Long userId) throws ElementNotFoundException{

        Iterable<Worktime> finalWorktime = worktimeRepository.findAllByUserIdAndWorktimeType(userId, WorktimeType.Unfinal);

        if (finalWorktime == null){

            throw new ElementNotFoundException("This element has been not found.");
        }else{

            return finalWorktime;
        }
    }

    public Iterable<Worktime> getAllMyFinalWorktime(long employeeNummer) throws ElementNotFoundException{

        Iterable<Worktime> finalWorktime = worktimeRepository.findAllByUserIdAndWorktimeType(employeeNummer, WorktimeType.Final);

        if (finalWorktime == null){

            throw new ElementNotFoundException("This element has been not found.");
        }else{

            return finalWorktime;
        }
    }

    public Iterable<Worktime> getAllMyWorktime(Long userId) throws ElementNotFoundException{

        Iterable<Worktime> worktimes = worktimeRepository.findAllByUserId(userId);

        if (worktimes == null){

            throw new ElementNotFoundException("This element has been not found.");
        }else{

            return worktimes;
        }
    }

    public List<Worktime> getAllMyWorktimeByProjectID(Long projectID) throws ElementNotFoundException{

        List<Worktime> worktimes = worktimeRepository.findByProjectId(projectID);

        if (worktimes == null){

            throw new ElementNotFoundException("This element has been not found.");
        }else{

            return worktimes;
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

    //test Methoden
    public Worktime getWorktimeById(Long id) throws ElementNotFoundException {

        Optional<Worktime> worktime = worktimeRepository.findById(id);

        if (worktime.isEmpty()){

            throw new ElementNotFoundException("This element has been not found.");
        }

        return worktime.get();
    }



    public Worktime updateUnfinalWorktime(Worktime newWorktime) throws ElementNotFoundException{

        Optional<Worktime> optional_unfinalWorktime = worktimeRepository.findById(newWorktime.getId());

        if (optional_unfinalWorktime.isPresent()){

            return worktimeRepository.save(newWorktime);

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

    public Iterable<Worktime> finaliseAllMyUnfinalWorktime(Long userId) {

        Iterable<Worktime> worktimes = worktimeRepository.findAllByUserIdAndWorktimeType(userId, WorktimeType.Unfinal);

        worktimes.forEach(worktime -> {
            worktime.setWorktimeType(WorktimeType.Final);
            worktimeRepository.save(worktime);

        });

        return worktimeRepository.findAllByUserIdAndWorktimeType(userId, WorktimeType.Final);
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
