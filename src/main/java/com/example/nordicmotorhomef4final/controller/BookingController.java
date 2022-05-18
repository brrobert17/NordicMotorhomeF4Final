package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.repo.BookingRepo;
import com.example.nordicmotorhomef4final.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;

    // Bookings html connector GET
    @GetMapping("bookings/bookingPage")
    public String showBooking(Model model) {
        List<Booking> bookingList = bookingService.showAllBookings();
        model.addAttribute("bookingList", bookingList);
        return "bookings/bookingPage";
    }

    @PostMapping("bookings/bookingPage")
    public String filterBooking(Model model, @Param("keyword") String keyword) {

       // MAKE A DINAMIC FILTER FOR SEARCH WITH KEYWORD

       List<Booking> bookingList = bookingService.showAllBookingKeyword(keyword);

        model.addAttribute("bookingList", bookingList);

        model.addAttribute("keyword", keyword);
        //model.addAttribute("bookingData", new Booking());


        return "bookings/bookingPage";
    }

    @GetMapping("bookings/update/{id}")
    public String updateBooking() {
        return "bookings/editBooking";
    }
}