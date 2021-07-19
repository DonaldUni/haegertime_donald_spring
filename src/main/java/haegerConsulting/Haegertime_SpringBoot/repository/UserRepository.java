package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.Customer;
import haegerConsulting.Haegertime_SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);

    Optional<User> findByEmployeeNummer(long employeeNummer);

    boolean existsByUserName(String username);

    void deleteByUserName(String username);
}
