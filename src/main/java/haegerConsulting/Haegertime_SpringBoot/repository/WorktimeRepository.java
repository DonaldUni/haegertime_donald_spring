package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.WorktimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorktimeRepository extends JpaRepository<Worktime, Long> {


    Optional<Worktime> findByIdAndWorktimeType(Long id, WorktimeType worktimeType); // zu testen, wenn der controller fertig wird

    Iterable<Worktime> findAllByUserId(Long userId);

    Iterable<Worktime> findAllByWorktimeType(WorktimeType worktimeType); // zu testen, wenn der controller fertig wird

    Iterable<Worktime> findAllByUserIdAndWorktimeType(Long userId, WorktimeType worktimeType); // zu testen, wenn der controller fertig wird

    void deleteAllByWorktimeType(WorktimeType worktimeType);

}