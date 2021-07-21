package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestOfHolidaysRepository extends JpaRepository<RequestOfHoliday, Long> {

    Iterable<RequestOfHoliday> findByUserId(Long userId);

    Optional<RequestOfHoliday> findById(Long id);

}
