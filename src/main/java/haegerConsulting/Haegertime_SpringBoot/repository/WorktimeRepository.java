package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.WorktimeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorktimeRepository extends JpaRepository<Worktime, Long> {

    Optional<Worktime> finbByIdAndWorktimeType(Long id, WorktimeType worktimeType); // zu testen, wenn der controller fertig wird

    Iterable<Worktime> findAllByUserId(Long userId);

    Iterable<Worktime> findAllByWorktimeType(WorktimeType worktimeType); // zu testen, wenn der controller fertig wird

    Iterable<Worktime> findAllByUserIdAndWortimeType(Long userId, WorktimeType worktimeType); // zu testen, wenn der controller fertig wird

    void deleteAllByWorktime(WorktimeType worktimeType);



}