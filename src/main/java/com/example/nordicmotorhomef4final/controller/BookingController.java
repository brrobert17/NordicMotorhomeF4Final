package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.repo.BookingRepo;
import com.example.nordicmotorhomef4final.service.BookingService;
import com.example.nordicmotorhomef4final.service.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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
        List<Booking> bookingList = bookingService.showAllBookingsByUser(keyword);
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("keyword", keyword);




        return "bookings/bookingPage";
    }
    @GetMapping("/bookings/new")
    public String showNewBooking(Model model) {
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Add new Booking");
        return "bookings/editBooking";
    }

    @PostMapping("/bookings/save")
    public String saveBookings(Booking booking, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Customer has been saved successfully.");
        bookingService.saveBooking(booking);
        return "redirect:bookings/bookingPage";
    }




    @GetMapping("/bookings/update/{id}")
    public String updateBooking(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Booking booking = bookingService.getBookingById(id);
            model.addAttribute("updateBooking", booking);
            model.addAttribute("pageTitle", "Edit booking (ID: "+ id+" )");
            redirectAttributes.addFlashAttribute("message", "Booking has been saved successfully.");
            return "/";
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "Bookings/bookingPage";
        }
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