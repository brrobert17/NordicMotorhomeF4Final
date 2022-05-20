package com.example.nordicmotorhomef4final.repo;

import com.example.nordicmotorhomef4final.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

//This class extends JPA Repository, which is a part of Spring.
//It allows us to link the objects we created in java to
//tables in the SQL database.
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

//    This query allows us retrieve a List of Vehicle objects if these contain a keyword in their attributes
    @Query("SELECT van FROM Vehicle van WHERE van.registrationPlate LIKE %?1%"
            + " OR van.brand LIKE %?1%"
            + " OR van.model LIKE %?1%"
            + " OR van.cLicense LIKE %?1%"
            + " OR CONCAT(van.capacity, '') LIKE %?1%")
    List<Vehicle> searchForKeyword(String keyword);

//    This query allows us retrieve a List of Vehicle objects having bookings
//    not comprised between the dates that we submit (= vehicles available for booking in those dates)
    @Query("SELECT v from Vehicle v JOIN v.bookings b "
            +"WHERE v.registrationPlate NOT in (" +
            "Select v.registrationPlate From Vehicle v JOIN v.bookings b where ((b.startDate BETWEEN ?1 AND ?2) and (b.endDate BETWEEN ?1 and ?2))) "+
            " GROUP BY v.registrationPlate")
    List<Vehicle> searchAvailable(LocalDate startDate, LocalDate endDate);

//    This query allows us to retrieve vehicles available for booking in the dates we pass
//    and search for a keyword present in their fields
    @Query("SELECT van from Vehicle van "+
            "WHERE van.registrationPlate NOT IN (Select v.registrationPlate From Vehicle v JOIN v.bookings b where ((b.startDate BETWEEN ?2 AND ?3) OR (b.startDate BETWEEN ?2 and ?3)))" +
            "   AND (van.registrationPlate LIKE %?1%"
            + " OR van.brand LIKE %?1%"
            + " OR van.model LIKE %?1%"
            + " OR van.cLicense LIKE %?1%"
            + " OR CONCAT(van.capacity, '') LIKE %?1%)" +
            "GROUP BY van.registrationPlate")
    List<Vehicle> searchKeywordAndDates(String keyword,LocalDate startDate, LocalDate endDate);

//    This query retieves a vehicle that has a given registration Plate
    Vehicle searchByRegistrationPlate(String registrationPlate);

}