package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.service.BookingService;
import com.example.nordicmotorhomef4final.service.CustomerService;
import com.example.nordicmotorhomef4final.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController2 {
    @Autowired
    BookingService bookingService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CustomerService customerService;

    // Bookings html connector GET
    @GetMapping("bookings/bookingPage")
    public String showBooking(Model model) {
        String keyword = "";
        List<Booking> bookingList = bookingService.showAllBookings();
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("keyword", keyword);
        return "bookings/bookingPage2";
    }

    //  Get Mapping for Adding New Vehicle

    @GetMapping("bookings/new")
    public String showNewCustomerForm(Model model,Vehicle vehicle,Booking booking) {
        List<Vehicle> vehicleList = null;
        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Add new Booking");
        model.addAttribute("vehicleList",vehicleList);
        return "bookings/editBookingForm2";
    }

    @PostMapping("bookings/newfromVehicles")
    public String newBookingFromVehicles(Model model,Vehicle vehicle,Booking booking) {
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        vehicleList.add(vehicle);
        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Add new Booking");
        model.addAttribute("vehicleList",vehicleList);
        return "bookings/editBookingForm2";
    }

    @PostMapping("bookings/new")
//    TODO Handle registration plate duplicate
    public String bookkingFormWithAvailableVans(Model model, Booking searchBooking, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Select an Available Van.");
        String keyword = null;
        List<Vehicle> vehicleList = vehicleService.showFilteredVehicles(keyword,searchBooking);
        List<Customer> customerList = customerService.listAll();
        model.addAttribute("customerList", customerList);
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("pageTitle", "Add new Booking");
        model.addAttribute("vehicleList", vehicleList);
        return "bookings/editBookingForm2";
    }



    @PostMapping("bookings/bookingPage")
    public String filterBooking(Model model, @Param("keyword") String keyword) {
        //MAKE A DINAMIC FILTER FOR SEARCH WITH KEYWORD




        return "bookings/bookingPage2";
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