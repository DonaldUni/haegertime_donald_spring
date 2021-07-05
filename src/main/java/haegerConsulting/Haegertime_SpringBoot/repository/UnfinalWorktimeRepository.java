package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.model.UnfinalWorktime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnfinalWorktimeRepository extends JpaRepository<UnfinalWorktime, Long> {

    Iterable<UnfinalWorktime> findAllByUserId(Long userId);

}