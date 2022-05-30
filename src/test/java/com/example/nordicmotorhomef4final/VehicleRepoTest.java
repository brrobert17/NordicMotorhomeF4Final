package com.example.nordicmotorhomef4final;

import com.example.nordicmotorhomef4final.repo.CustomerRepo;
import com.example.nordicmotorhomef4final.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class VehicleRepoTest {
    @Autowired
    private VehicleRepo vehicleRepo;






}