package com.example.nordicmotorhomef4final.repo;

import com.example.nordicmotorhomef4final.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking AS b WHERE CONCAT(b.bookingId, b.customer.firstName, b.customer.lastName," +
            " b.customer.phoneNumber, b.vehicle.brand, b.vehicle.model, b.vehicle.registrationPlate)" +
            " LIKE %?1%")
    List<Booking> searchBookingByKeyword(String keyword);

    @Query("SELECT b FROM Booking AS b WHERE b.startDate = ?1 AND b.endDate = ?2")
    List<Booking> searchBookingByDate(LocalDate startDate, LocalDate endStart);

    @Query("SELECT b FROM Booking AS b WHERE b.startDate = ?1 AND b.endDate = ?2 AND CONCAT(b.bookingId, b.customer.firstName, b.customer.lastName," +
            " b.customer.phoneNumber, b.vehicle.brand, b.vehicle.model, b.vehicle.registrationPlate) LIKE %?3%")
    List<Booking> searchBookingByDateAndKeyword(LocalDate startDate, LocalDate endStart, String keyword);
}