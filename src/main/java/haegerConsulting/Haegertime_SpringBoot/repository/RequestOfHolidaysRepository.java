package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import haegerConsulting.Haegertime_SpringBoot.model.RequestOfHoliday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestOfHolidaysRepository extends JpaRepository<RequestOfHoliday, Long> {

    Iterable<RequestOfHoliday> findAllByUserId(Long userId);

}
