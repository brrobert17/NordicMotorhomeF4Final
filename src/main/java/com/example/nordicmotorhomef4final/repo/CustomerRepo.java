package com.example.nordicmotorhomef4final.repo;

import com.example.nordicmotorhomef4final.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    // This method is used by the "deleteCustomerById" method in the "CustomerService"
    // to make sure that the customer object we're trying to delete exists before we go through with it
    Long countByCustomerId(Integer id);

    // This method uses a MySQL query that's the heart of the search function
    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE %?1%"
            + " OR c.lastName LIKE %?1%"
            + " OR c.licenseNumber LIKE %?1%"
    )
    List<Customer> searchCustomer(@Param("keyword") String keyword);
}
