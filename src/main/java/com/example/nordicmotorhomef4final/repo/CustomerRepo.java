package com.example.nordicmotorhomef4final.repo;

import com.example.nordicmotorhomef4final.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
    public Long countById(Integer id);
}
