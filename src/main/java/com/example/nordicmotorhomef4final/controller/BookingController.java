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

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;

    // Bookings html connector GET
    @GetMapping("bookings/bookingPage")
    public String showBooking(Model model) {
        String keyword = "";
        List<Booking> bookingList = bookingService.showAllBookings();
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("keyword", keyword);
        return "bookings/bookingPage";
    }
    @PostMapping("bookings/bookingPage")
    public String filterBooking(Model model, @Param("keyword") String keyword) {
        //MAKE A DINAMIC FILTER FOR SEARCH WITH KEYWORD




        return "bookings/bookingPage";
    }
}

/*
//    Bookings html connector Post
    @PostMapping("bookings/bookingPage")
    public String searchBooking(Model model, Booking bookings) {
        List<Booking> listBooking = bookingRepo.findByVehicleRegistrationPlate(bookings.getVehicle().getRegistrationPlate().toString());
        if (bookings.getVehicle().getRegistrationPlate().toString().isEmpty()) {
            System.out.println("isempty");
            listBooking = bookingRepo.findAll();
        }
        model.addAttribute("listBooking", listBooking);
        model.addAttribute("bookinG", new Booking());
        model.addAttribute("searchBook", new Booking());
        return "bookings/bookingPage";
    }
 */