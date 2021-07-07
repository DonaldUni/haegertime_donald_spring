package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.Customer.CustomerNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.Customer.DuplicateCustomerException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.WorktimeExceptions.WorktimeNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.model.Customer;
import haegerConsulting.Haegertime_SpringBoot.model.Worktime;
import haegerConsulting.Haegertime_SpringBoot.model.enumerations.WorktimeType;
import haegerConsulting.Haegertime_SpringBoot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;



    public Customer create(Customer customer) throws DuplicateCustomerException {

        if (customer.getId() != null && customerRepository.existsById(customer.getId())){

            throw new DuplicateCustomerException();
        }

        return customerRepository.save(customer);
    }

    public void updateCustomer(Customer newCustomer) throws CustomerNotFoundException {

        Optional<Customer> optionalCustomer = customerRepository.findById(newCustomer.getId());

        if (optionalCustomer.isPresent()){

            customerRepository.save(newCustomer);
        }else {

            throw new CustomerNotFoundException();
        }
    }

    public Customer getCustomer(Long id) throws CustomerNotFoundException{

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()){

            throw new CustomerNotFoundException();
        }

        return customer.get();
    }

    public List<Customer> getAllCustomers(){

        return customerRepository.findAll();
    }

    public void deleteById(Long id) throws CustomerNotFoundException {

        if (customerRepository.existsById(id)){

            customerRepository.deleteById(id);
        }else {

            throw new CustomerNotFoundException();
        }
    }

    public void deleteAll(){

        customerRepository.deleteAll();
    }
}
