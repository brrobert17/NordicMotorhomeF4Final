package com.example.nordicmotorhomef4final.repo;


import com.example.nordicmotorhomef4final.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByVehicleRegistrationPlate(String s);
    Optional<Booking> findByBookingId(int i);

}

