package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    public List<Booking> showAllBookings() {
        return bookingRepo.findAll();
    }

    public List<Booking> showAllBookingsByUser(String bookingkeyword) {
        if (bookingkeyword != null) {
            return bookingRepo.searchForKeywordBooking(bookingkeyword);
        }
        return bookingRepo.findAll();
        //



    }
    //get booking by id
    public Booking getBookingById(Integer id) throws CustomerNotFoundException {
       Optional<Booking> result=bookingRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new CustomerNotFoundException("Did not find booking id - "+id);
    }
    //save booking
    public void saveBooking(Booking booking) {
        bookingRepo.save(booking);
    }



}
