package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.EntityNotFoundException;
import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public List<Booking> showAllBookings() {
        return bookingRepo.findAll();
    }
    public void saveBooking(Booking booking) {
        bookingRepo.save(booking);
    }
    public Booking getBookingById(Integer id) throws EntityNotFoundException {
        Optional<Booking> bookingRepoById = bookingRepo.findById(id);
        if(bookingRepoById.isPresent()) {
            return bookingRepoById.get();
        }
        throw new EntityNotFoundException("Booking with id " + id + " not found");

    }
    //get booking by id with optional and twrow exception if not found



    public List<Booking> showFilteredBookings(String keyword, Booking searchBooking) {
        if (searchBooking.getStartDate() == null || searchBooking.getEndDate() == null) {
            if (keyword == null) {
                return bookingRepo.findAll();
            } else {
                return bookingRepo.searchBookingByKeyword(keyword);
            }
        } else {
            if (keyword == null) {
                return bookingRepo.searchBookingByDate(searchBooking.getStartDate(), searchBooking.getEndDate());
            } else {
                return bookingRepo.searchBookingByDateAndKeyword(searchBooking.getStartDate(), searchBooking.getEndDate(), keyword);
            }
        }
    }
    //show only a booking by id
    public List<Booking> showAllBookingKeyword(String keyword) {
        return bookingRepo.searchBookingByKeyword(keyword);
    }

}





    //make a general search function that can search by keyword and date




