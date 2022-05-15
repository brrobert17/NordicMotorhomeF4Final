package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    BookingRepo bookingRepo;

    //    Bookings html connector GET
    @GetMapping("booking/bookingsPage")
    public String showBooking(Model model) {
        List<Booking> listBooking = bookingRepo.findAll();
        model.addAttribute("listBooking", listBooking);
        model.addAttribute("bookinG", new Booking());
        model.addAttribute("searchBook", new Booking());
        return "booking/bookingsPage";
    }

    //    Bookings html connector Post
    @PostMapping("booking/bookingsPage")
    public String searchBooking(Model model, Booking bookings) {
        List<Booking> listBooking = bookingRepo.findByVehicleRegistrationPlate(bookings.getVehicle().getRegistrationPlate().toString());
        if (bookings.getVehicle().getRegistrationPlate().toString().isEmpty()) {
            System.out.println("isempty");
            listBooking = bookingRepo.findAll();
        }
        model.addAttribute("listBooking", listBooking);
        model.addAttribute("bookinG", new Booking());
        model.addAttribute("searchBook", new Booking());
        return "booking/bookingsPage";
    }

}
