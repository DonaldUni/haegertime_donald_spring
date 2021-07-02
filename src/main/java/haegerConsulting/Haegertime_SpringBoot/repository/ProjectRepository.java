package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<FinalWorktime, Long> {

    Iterable<FinalWorktime> findAllByUserId(Long userId);

}
