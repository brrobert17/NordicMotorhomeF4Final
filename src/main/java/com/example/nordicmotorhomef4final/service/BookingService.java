package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    //filter by start date, end date and keyword


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
//show only a booking with a keyword




    //make a general search function that can search by keyword and date




