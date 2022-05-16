package com.example.nordicmotorhomef4final.repo;

import com.example.nordicmotorhomef4final.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    @Query("SELECT van FROM Vehicle van WHERE van.registrationPlate LIKE %?1%"
            + " OR van.brand LIKE %?1%"
            + " OR van.model LIKE %?1%"
            + " OR van.cLicense LIKE %?1%"
            + " OR CONCAT(van.capacity, '') LIKE %?1%")
    List<Vehicle> searchForKeyword(String keyword);


    @Query("SELECT v from Vehicle v JOIN v.bookings b "
            +"WHERE v.registrationPlate NOT in (" +
            "Select v.registrationPlate From Vehicle v JOIN v.bookings b where ((b.startDate BETWEEN ?1 AND ?2) and (b.endDate BETWEEN ?1 and ?2))) "+
            " GROUP BY v.registrationPlate")
    List<Vehicle> searchAvailable(String startDate, String endDate);

    @Query("SELECT van from Vehicle van "+
            "WHERE van.registrationPlate NOT IN (Select v.registrationPlate From Vehicle v JOIN v.bookings b where ((b.startDate BETWEEN ?2 AND ?3) OR (b.startDate BETWEEN ?2 and ?3)))" +
            "   AND (van.registrationPlate LIKE %?1%"
            + " OR van.brand LIKE %?1%"
            + " OR van.model LIKE %?1%"
            + " OR van.cLicense LIKE %?1%"
            + " OR CONCAT(van.capacity, '') LIKE %?1%)" +
            "GROUP BY van.registrationPlate")
    List<Vehicle> searchKeywordAndDates(String keyword,String startDate, String endDate);


}