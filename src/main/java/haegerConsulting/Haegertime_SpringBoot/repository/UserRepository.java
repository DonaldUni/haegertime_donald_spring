package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);


    boolean existsByUserName(String username);

    void deleteByUserName(String username);
}
