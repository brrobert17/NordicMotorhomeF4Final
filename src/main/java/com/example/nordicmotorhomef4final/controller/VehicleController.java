package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.service.CustomerNotFoundException;
import com.example.nordicmotorhomef4final.service.VehicleService;

import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

//  Get Mapping for Vehicle Page
    @GetMapping("vehicles/vehiclePage")
    public String showVehicles(Model model) {
        String keyword = "";
        String available = "Insert Date";
        List<Vehicle> vehiclesList = vehicleService.showAllVehicles();
        model.addAttribute("vehiclesList", vehiclesList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("available", available);
        model.addAttribute("searchDateVehicle", new Booking());
        return "vehicles/vehiclePage";
    }

//    Post Mapping for Vehicle Page
    @PostMapping("vehicles/vehiclePage")
    public String filterVehicles(Model model,
                                 @Param("keyword") String keyword,
                                 Booking searchBooking) {
        String available = "Insert Date";
        //keyword is the search word, searchBooking is a booking object with Dates inside
        List<Vehicle> vehiclesList = vehicleService.showFilteredVehicles(keyword,searchBooking);
        //Passes the String Available to the page if the dates are not null
            if (searchBooking.getStartDate() != null && searchBooking.getEndDate() != null) {
                    available = "Available: "+searchBooking.getStartDate()+ " "+searchBooking.getEndDate();
                }
        model.addAttribute("vehiclesList", vehiclesList);
        model.addAttribute("searchDateVehicle", new Booking());
        model.addAttribute("available", available);
//        TODO
//         handle only one date inputted
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
//    TODO Handle registration plate duplicate
    public String saveNewVehicle(Vehicle vehicle, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Vehicle has been saved successfully.");
        vehicleService.saveVehicle(vehicle);
        return "redirect:vehiclePage";
    }

    @GetMapping("/edit/{registrationPlate}")
    public String editVehicle(@PathVariable("registrationPlate") String registrationPlate, Model model, RedirectAttributes redirectAttributes) {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(registrationPlate);
            System.out.println("vehicle");
            model.addAttribute("newVehicle", vehicle);
            model.addAttribute("pageTitle", "Edit vehicle (Reg: "+ registrationPlate+" )");
            redirectAttributes.addFlashAttribute("message", "Vehicle has been saved successfully.");
            return "vehicles/editVehicleForm";
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/vehiclePage";
        }
    }

    @GetMapping("/delete/{registrationPlate}")
    public String deleteCustomer(@PathVariable("registrationPlate") String registrationPlate, RedirectAttributes redirectAttributes) {
        try {
            vehicleService.deleteVehicle(vehicleService.getVehicleById(registrationPlate));
            redirectAttributes.addFlashAttribute("message", "Vehicle: "+ registrationPlate +" has been deleted.");
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/vehiclePage";
    }

}
