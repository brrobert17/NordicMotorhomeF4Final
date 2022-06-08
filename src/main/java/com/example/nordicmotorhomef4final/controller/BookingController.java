package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.List;

//Controller is a class that handles the requests from the user and returns a response to the user.
@Controller
public class BookingController {
    //Autowired is a spring annotation that tells spring to inject the dependency of the BookingService class.
    @Autowired
    BookingService bookingService;

    // show all bookings in the database
    //@GetMapping only apply only on class level and requestMapping apply on lass level and method level
    @GetMapping("bookings/bookingPage")
    public String showBooking(Model model) {
        List<Booking> bookingList = bookingService.showAllBookings();
        model.addAttribute("bookingList", bookingList);
        return "bookings/bookingPage";
    }
    //@Param is used to get the value from the html form and put it in the method.
    //show all bookings in the database according to the "keyword"
    //@postmapping new annotation for spring mvc
    //String, Intiger is a wrapper class and its not primitive
    @PostMapping("bookings/bookingPage")
    public String filterBooking(Model model, @RequestParam ("keyword") String keyword) {
        // MAKE A DINAMIC FILTER FOR SEARCH WITH KEYWORD
        List<Booking> bookingList = bookingService.showAllBookingKeyword(keyword);
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("keyword", keyword);
        //model.addAttribute("bookingData", new Booking());

        return "bookings/bookingPage";
    }

    //direct us to new booking form and creat object of new booking and add it to the model

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
// add a new customer to based on {customerId} return to new html page in newBookingForm
    @GetMapping("/bookings/addToNewBooking/{customerId}")
    public String addCustomerToBooking(
            @PathVariable Integer customerId, Model model) {

        model.addAttribute("customerId", customerId);
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Make New Booking");

        return "bookings/newBookingForm";
    }
    //@pathvariable is dinamic way of getting the value from the html form
    //@parthvariable "" key inside the curly braces should be the same in order to be matched and identified we dont need
    // "customerId"or "registerationPlate" after @pathVariable since {customerId} is same as "customerId" after Integer
    //get the customerId, registrationPlate and stratDate and EndDate from the html form and put it in the make a new booking
    @GetMapping("/bookings/addToNewBooking/{customerId}/{registrationPlate}/{bookStart}/{bookEnd}")
    public String addCustomerToBooking(
            @PathVariable Integer customerId,
            @PathVariable String registrationPlate,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @PathVariable LocalDate bookStart,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @PathVariable LocalDate bookEnd,
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
    public String updateBooking(@PathVariable Integer bookingId, Model model) {
        //get the booking from the database
        Booking booking = bookingService.findBookingById(bookingId);
        //creat a booking list and put all booking id which have the same customer id
        List<Booking> checkregisrtationplate= bookingService.checkRegisrtationPlateIsAvailable(bookingId);

        boolean checkdate= bookingService.checkCollision(bookingId);



        model.addAttribute("checkVehicle", checkregisrtationplate);
        model.addAttribute("booking", booking);

        model.addAttribute("checkdate",  checkdate);

        model.addAttribute("newBooking", booking);
        model.addAttribute("pageTitle", "Update Booking with ID: " + bookingId);


        return "bookings/updateBookingForm";
    }
    //delete booking from database and put in try and catch to make sure the id is exist
    @GetMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.deleteBookingById(id);
            //redirectAttributes is used to add a message to the page after the delete

            redirectAttributes.addFlashAttribute("message", "Customer ID: " + id + " has been delete successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/bookings/bookingPage";
    }
    //print the booking and find the booking by id and find days and total price and add it to the html page
    @GetMapping("/bookings/print/{bookingId}")
    public String printBooking(@PathVariable Integer bookingId, Model model) {
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
