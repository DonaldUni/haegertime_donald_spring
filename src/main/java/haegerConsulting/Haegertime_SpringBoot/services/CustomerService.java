package haegerConsulting.Haegertime_SpringBoot.services;

import haegerConsulting.Haegertime_SpringBoot.exceptions.ElementNotFoundException;
import haegerConsulting.Haegertime_SpringBoot.exceptions.DuplicateException;
import haegerConsulting.Haegertime_SpringBoot.model.Customer;
import haegerConsulting.Haegertime_SpringBoot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;



    public Customer create(Customer customer) throws DuplicateException {

        if (customer.getId() != null && customerRepository.existsById(customer.getId())){

            throw new DuplicateException("This element already exist.");
        }

        return customerRepository.save(customer);
    }

    public void updateCustomer(Customer newCustomer) throws ElementNotFoundException {

        Optional<Customer> optionalCustomer = customerRepository.findById(newCustomer.getId());

        if (optionalCustomer.isPresent()){

            customerRepository.save(newCustomer);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public Customer getCustomer(Long id) throws ElementNotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()){

            throw new ElementNotFoundException("This element has been not found.");
        }

        return customer.get();
    }

    public Customer getCustomerByCustomerId(long customer_id) throws ElementNotFoundException {

        Optional<Customer> customer = customerRepository.findByCustomerId(customer_id);

        if (customer.isEmpty()){

            throw new ElementNotFoundException("This element has been not found.");
        }

        return customer.get();
    }

    public List<Customer> getAllCustomers(){

        return customerRepository.findAll();
    }

    public void deleteById(Long id) throws ElementNotFoundException {

        if (customerRepository.existsById(id)){

            customerRepository.deleteById(id);
        }else {

            throw new ElementNotFoundException("This element has been not found.");
        }
    }

    public void deleteAll(){

        customerRepository.deleteAll();
    }
}
