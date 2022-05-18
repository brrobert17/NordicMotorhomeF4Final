package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "newBookingForm";
    }
    @GetMapping("bookings/new")
    public String showNewBookingForm(Model model) {
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Make New Booking");
        return "bookings/newBookingForm";
    }

    @PostMapping("bookings/save")
    public String saveBooking(Booking booking, RedirectAttributes redirectAttributes) {
        bookingService.saveBooking(booking);
        redirectAttributes.addFlashAttribute("message", "The booking has been saved successfully");
        return "redirect:/bookings/bookingPage";
    }
}