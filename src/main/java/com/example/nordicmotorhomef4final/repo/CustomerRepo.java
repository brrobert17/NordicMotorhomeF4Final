package com.example.nordicmotorhomef4final.repo;

import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.model.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
    public Long countByCustomerId(Integer id);

    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE %?1%"
            + " OR c.lastName LIKE %?1%"
            + " OR c.licenseNumber LIKE %?1%"
            )
    List<Customer> searchCustomer(@Param("keyword") String keyword);
}
