package com.example.nordicmotorhomef4final.repo;

import com.example.nordicmotorhomef4final.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//JpaRepository is a class that extends CrudRepository
//@Query is a method that is used to create a query in the database

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    //countByBookingId is a method that counts the number of bookings with the same id
    public Long countByBookingId(int bookingId);
//creat a search query that searches for a booking by keyword
    @Query("SELECT b FROM Booking AS b WHERE CONCAT(b.bookingId, b.customer.firstName, b.customer.lastName," +
            " b.customer.phoneNumber, b.vehicle.brand, b.vehicle.model, b.vehicle.registrationPlate)" +
            " LIKE %?1%")
    List<Booking> searchBookingByKeyword(String keyword);

   /* @Query("SELECT b FROM Booking AS b WHERE b.startDate = ?1 AND b.endDate = ?2")
    List<Booking> searchBookingByDate(LocalDate startDate, LocalDate endStart);*/
//
    /*@Query("SELECT b FROM Booking AS b WHERE b.startDate = ?1 AND b.endDate = ?2 AND CONCAT(b.bookingId, b.customer.firstName, b.customer.lastName," +
            " b.customer.phoneNumber, b.vehicle.brand, b.vehicle.model, b.vehicle.registrationPlate) LIKE %?3%")
    List<Booking> searchBookingByDateAndKeyword(LocalDate startDate, LocalDate endStart, String keyword);*/

//find a bookingById and return it
    @Query("SELECT b FROM Booking AS b WHERE b.bookingId = ?1")
    Booking getBookingById(Integer id);

//findAllByVehicle_RegistrationPlate in the database
    @Query("SELECT b FROM Booking AS b WHERE b.vehicle.registrationPlate = ?1")
    List<Booking> findAllByVehicle_RegistrationPlate(String registrationPlate);









}