package com.example.nordicmotorhomef4final;

import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.repo.CustomerRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CustomerRepoTest {
    @Autowired private CustomerRepo customerRepo;

    @Test
    public void testAddNew() {
        Customer customer = new Customer();
        customer.setFirstName("Róbert");
        customer.setLastName("Bári");
        customer.setcLicense("yes");
        customer.setLicenseNumber("aaa000");
        customer.setDateOfBirth(LocalDate.of(1995,1,1));
        customer.setPhoneNumber(1234567);

        Customer savedCustomer = customerRepo.save(customer);

        Assertions.assertThat(savedCustomer).isNotNull();
        Assertions.assertThat(savedCustomer.getCustomerId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Customer> customers = customerRepo.findAll();
        Assertions.assertThat(customers).hasSizeGreaterThan(0);
        for (Customer customer: customers
             ) {
            System.out.println(customer);
        }
    }

    @Test
    public void testUpdate() {
        Integer customerId = 101;
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        Customer customer = optionalCustomer.get();
        customer.setPhoneNumber(666);
        customerRepo.save(customer);

        Customer updatedCustomer = customerRepo.findById(customerId).get();
        Assertions.assertThat(updatedCustomer.getPhoneNumber()).isEqualTo(666);
    }

    @Test
    public void testGet() {
        Integer customerId = 101;
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        Assertions.assertThat(optionalCustomer).isPresent();
        System.out.println(optionalCustomer.get());
    }

    @Test
    public void testDelete() {
        Integer customerId = 101;
        customerRepo.deleteById(customerId);
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        Assertions.assertThat(optionalCustomer).isNotPresent();

    }

}
