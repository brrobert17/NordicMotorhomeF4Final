package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired private CustomerRepo customerRepo;

    // returning all the customers
    public List<Customer> listAll() {
        return (List<Customer>) customerRepo.findAll();
    }
    // saving a customer into the database
    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }
    // finding a customer by its customerId
    public Customer getCustomerById(Integer id) throws Exception {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new Exception("Could not find any customers with the ID: " + id);
    }
    // deleting a customer by its customerId
    @Transactional
    public void deleteCustomerById(Integer id) throws Exception {
        Long count = customerRepo.countByCustomerId(id);
        if (count== null || count ==0) {
            throw new Exception("Could not find any customers with the ID: " + id);
        }
        customerRepo.deleteById(id);
    }
    // searching for a customer based on a submitted keyword
    public List<Customer> searchCustomer(String keyword) {
        return customerRepo.searchCustomer(keyword);
    }

}
