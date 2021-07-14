package haegerConsulting.Haegertime_SpringBoot.repository;

import haegerConsulting.Haegertime_SpringBoot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
