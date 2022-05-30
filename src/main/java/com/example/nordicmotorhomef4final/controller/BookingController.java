package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.service.BookingService;
import com.example.nordicmotorhomef4final.service.CustomerNotFoundException;
import lombok.var;
import org.apache.tomcat.jni.FileInfo;
import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
//Controller is a class that handles the requests from the user and returns a response to the user.
@Controller
public class BookingController {
    //Autowired is a spring annotation that tells spring to inject the dependency of the BookingService class.
    @Autowired
    BookingService bookingService;

    // show all bookings in the database
    @GetMapping("bookings/bookingPage")
    public String showBooking(Model model) {
        List<Booking> bookingList = bookingService.showAllBookings();
        model.addAttribute("bookingList", bookingList);
        return "bookings/bookingPage";
    }
//@Param is used to get the value from the html form and put it in the method.
    //show all bookings in the database according to the "keyword"
    //@postmapping is
    @PostMapping("bookings/bookingPage")
    public String filterBooking(Model model, @Param("keyword") String keyword) {
        // MAKE A DINAMIC FILTER FOR SEARCH WITH KEYWORD
        List<Booking> bookingList = bookingService.showAllBookingKeyword(keyword);
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("keyword", keyword);
        //model.addAttribute("bookingData", new Booking());

        return "bookings/bookingPage";
    }

//creat a new booking in the database

    @GetMapping("bookings/new")
    public String showNewBookingForm(Model model) {
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Make New Booking");
        return "bookings/newBookingForm";
    }
//@postmapping is save the booking in the database
    @PostMapping("bookings/save")
    public String saveBooking(Booking booking, RedirectAttributes redirectAttributes) {
        bookingService.saveBooking(booking);
        redirectAttributes.addFlashAttribute("message", "The booking has been saved successfully");
        return "redirect:/bookings/bookingPage";
    }
//
    @GetMapping("/bookings/addToNewBooking/{customerId}")
    public String addCustomerToBooking(
            @PathVariable("customerId") Integer customerId, Model model) {

        model.addAttribute("customerId", customerId);
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Make New Booking");

        return "bookings/newBookingForm";
    }
    //pathvariable is dinamic way of getting the value from the html form
    //addCustomerToBooking is the name of the method for
    @GetMapping("/bookings/addToNewBooking/{customerId}/{registrationPlate}/{bookStart}/{bookEnd}")
    public String addCustomerToBooking(
            @PathVariable("customerId") Integer customerId,
            @PathVariable("registrationPlate") String registrationPlate,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @PathVariable("bookStart") LocalDate bookStart,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @PathVariable("bookEnd") LocalDate bookEnd,
            Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("registrationPlate", registrationPlate);
        model.addAttribute("bookStart", bookStart);
        model.addAttribute("bookEnd", bookEnd);
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Make New Booking");

        return "bookings/newBookingForm";
    }
//use @pathvariable to get the id from the url
    @GetMapping("/update/{bookingId}")
    public String updateBooking(@PathVariable("bookingId") Integer bookingId, Model model) {
        Booking booking = bookingService.findBookingById(bookingId);
       List<Booking> checkregisrtationplate= bookingService.checkRegisrtationPlateIsAvailable(bookingId);
      boolean checkdate= bookingService.checkCollision(bookingId);



       model.addAttribute("checkVehicle", checkregisrtationplate);
        model.addAttribute("booking", booking);
        model.addAttribute("checkdate", checkdate);


        model.addAttribute("newBooking", booking);
        model.addAttribute("pageTitle", "Update Booking with ID: " + bookingId);

        return "bookings/updateBookingForm";
    }

    @GetMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.deleteBookingById(id);

            redirectAttributes.addFlashAttribute("message", "Customer ID: " + id + " has been saved successfully.");
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/bookings/bookingPage";
    }

    @GetMapping("/bookings/print/{bookingId}")
    public String printBooking(@PathVariable("bookingId") Integer bookingId, Model model) {
        Booking booking = bookingService.findBookingById(bookingId);

        int days = bookingService.calculateDays(booking);
        double totalPrice = bookingService.calculateTotalPrice(booking);

        model.addAttribute("days", days);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("booking", booking);
        model.addAttribute("pageTitle", "Print Receipt");

        return "bookings/printBookingReceipt";

    }
    


}
