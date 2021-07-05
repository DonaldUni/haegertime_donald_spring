package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.FinalWorktime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.springframework.http.HttpHeaders.FROM;

public interface FinalWorktimeRepository extends JpaRepository<FinalWorktime, Long> {


    //@Query("FROM FinalWorktime WHERE userId = ?1" )
    Iterable<FinalWorktime> findAllByUserId(Long userId);

//    @Query("SELECT f FROM FinalWorktime WHERE f.userId = ?1" )
//    Iterable<FinalWorktime> findAllByUserId(Long userId);
//
//    @Query(value = "SELECT * FROM FinalWorktime WHERE  USER_ID = :userId", nativeQuery = true )
//    Iterable<FinalWorktime> findAllByUserId(@Param("userId") Long userId);

}
