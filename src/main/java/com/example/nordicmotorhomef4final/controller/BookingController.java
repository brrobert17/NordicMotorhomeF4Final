package com.example.nordicmotorhomef4final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class BookingController {
//    @Autowired
//    BookingRepo bookingRepo;

    //    Bookings html connector GET
    @GetMapping("/templates/bookingsPage.html")
    public String showBooking(Model model) {
//        List<Booking> listBooking = bookingRepo.findAll();
//        model.addAttribute("listBooking", listBooking);
//        model.addAttribute("bookinG", new Bookings());
//        model.addAttribute("searchBook", new Bookings());
        return "bookingsPage";
    }

    //    Bookings html connector Post
    @PostMapping("/templates/bookingsPage.html")
    public String searchBooking(Model model){// Bookings bookings) {
//        List<Bookings> listBooking = bookingRepo.findByRegistration(bookings.getRegistration().toString());
//        if (bookings.getRegistration().toString().isEmpty()) {
//            System.out.println("isempty");
//            listBooking = bookingRepo.findAll();
//        }
//        model.addAttribute("listBooking", listBooking);
//        model.addAttribute("bookinG", new Bookings());
//        model.addAttribute("searchBook", new Bookings());
        return "bookingsPage";
    }

}
