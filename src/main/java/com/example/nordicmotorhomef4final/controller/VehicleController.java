package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.model.Vehicle;

import com.example.nordicmotorhomef4final.service.CustomerService;
import com.example.nordicmotorhomef4final.service.VehicleService;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CustomerService customerService;

    //  Get Mapping for Vehicle Page
    @GetMapping("vehicles/vehiclePage")
    public String showVehicles(Model model) {
        String keyword = "";
        String available = "Insert Date";
        String titlePage = "Nordic Motorhome Vehicles";
        List<Vehicle> vehiclesList = vehicleService.showAllVehicles();
        List<String> brandList = vehicleService.brandList();
        model.addAttribute("titlePage", titlePage);
        model.addAttribute("vehiclesList", vehiclesList);
        model.addAttribute("brandList", brandList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("available", available);
        model.addAttribute("searchDateVehicle", new Booking());
        model.addAttribute("searchedBooking", new Booking());
        return "vehicles/vehiclePage";
    }

    //   1st    Post Mapping for Vehicle Page
    @PostMapping("/vehicles/vehiclePage")
    public String filterVehicles(Model model,
                                 @Param("keyword") String keyword,

                                 @Param("customerId") Integer customerId,
                                 Booking searchBooking) {
        String available = "Insert Date";
        //keyword is the search word, searchBooking is a booking object with Dates inside
        List<Vehicle> vehiclesList = vehicleService.showFilteredVehicles(keyword, searchBooking);
        //Passes the String Available to the page if the dates are not null
        if (searchBooking.getStartDate() != null && searchBooking.getEndDate() != null) {
            available = "Available: " + searchBooking.getStartDate() + " " + searchBooking.getEndDate();
        }
        String titlePage = "Nordic Motorhome Vehicles";
        model.addAttribute("titlePage", titlePage);
        model.addAttribute("customerId", customerId);
        model.addAttribute("vehiclesList", vehiclesList);
        model.addAttribute("searchDateVehicle", new Booking());
        model.addAttribute("available", available);
        model.addAttribute("searchedBooking", searchBooking);
        return "vehicles/vehiclePage";
    }



    // TODO by Zaland and Soheil
    // Connect to this getmapping!!

    //  Get Mapping for choosing Vehicle After having Chosen the Customer
    @SneakyThrows
    @GetMapping("vehicles/vehiclePage/{customerIdFromBooking}")
    public String selectVehiclesAfterCustomer(@PathVariable("customerIdFromBooking") Integer customerIdFromBooking,
                                              Model model, RedirectAttributes redirectAttributes) {
        String keyword = "";
        String titlePage = "Choose a vehicle for " + customerService.getCustomerById(customerIdFromBooking).getFirstName()
                + " " + customerService.getCustomerById(customerIdFromBooking).getLastName();
        String available = "Insert Date";
        Integer custPickId = 0;
        model.addAttribute("titlePage", titlePage);
        model.addAttribute("customerIdFromBooking", customerIdFromBooking);
        model.addAttribute("vehiclesList", vehicleService.showAllVehicles());
        model.addAttribute("brandList", vehicleService.brandList());
        model.addAttribute("keyword", keyword);
        model.addAttribute("available", available);
        model.addAttribute("searchDateVehicle", new Booking());
        model.addAttribute("searchedBooking", new Booking());

        return "vehicles/vehiclePage";
    }


    //  2nd   Post Mapping for Vehicle Page [for choosing Vehicle After having Chosen the Customer]
    @SneakyThrows
    @PostMapping("/vehicles/vehiclePageWithCustomer")
    public String filterVehiclesForCustomer(Model model,
                                            @Param("customerPickedId") Integer customerPickedId,
                                            @Param("key") Integer customerId,
                                            @Param("keyword") String keyword,
                                            Booking searchBooking) {
        String available = "Insert Date";
        //keyword is the search word, searchBooking is a booking object with Dates inside
        List<Vehicle> vehiclesList = vehicleService.showFilteredVehicles(keyword, searchBooking);
        //Passes the String Available to the page if the dates are not null
        if (searchBooking.getStartDate() != null && searchBooking.getEndDate() != null) {
            available = "Available: " + searchBooking.getStartDate() + " " + searchBooking.getEndDate();
        }
        String titlePage = "Choose a vehicle for " + customerService.getCustomerById(customerId).getFirstName()
                + " " + customerService.getCustomerById(customerId).getLastName();
        model.addAttribute("titlePage", titlePage);
        model.addAttribute("customerPickedId", customerId);
        model.addAttribute("vehiclesList", vehiclesList);
        model.addAttribute("searchDateVehicle", new Booking());
        model.addAttribute("available", available);
        model.addAttribute("searchedBooking", searchBooking);
        return "vehicles/vehiclePage";
    }


    //  Get Mapping for Adding New Vehicle
    @GetMapping("vehicles/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("newVehicle", new Vehicle());
        model.addAttribute("pageTitle", "Add new Vehicle");
        return "vehicles/editVehicleForm";
    }

    @PostMapping("vehicles/save")
//    vehicle is a string that saves the vehicle and returns ok or duplicate message
    public String saveNewVehicle(Vehicle vehicle, RedirectAttributes redirectAttributes) {
        RedirectAttributes due = redirectAttributes;
        redirectAttributes.addFlashAttribute(vehicleService.saveVehicle(vehicle, due));
        return "redirect:vehiclePage";
    }

    @GetMapping("/edit/{registrationPlate}")
    public String editVehicle(@PathVariable("registrationPlate") String registrationPlate, Model model, RedirectAttributes redirectAttributes) {
        Vehicle vehicle = vehicleService.getVehicleById(registrationPlate);
        System.out.println("vehicle");
        model.addAttribute("newVehicle", vehicle);
        model.addAttribute("pageTitle", "Edit vehicle (Reg: " + registrationPlate + " )");
        redirectAttributes.addFlashAttribute("message", "Vehicle has been saved successfully.");
        return "vehicles/editVehicleForm";

    }

    @GetMapping("/delete/{registrationPlate}")
    public String deleteCustomer(@PathVariable("registrationPlate") String registrationPlate, RedirectAttributes redirectAttributes) {
        vehicleService.deleteVehicle(vehicleService.getVehicleById(registrationPlate));
        redirectAttributes.addFlashAttribute("message", "Vehicle: " + registrationPlate + " has been deleted.");
        return "redirect:../vehicles/vehiclePage";
    }

}
