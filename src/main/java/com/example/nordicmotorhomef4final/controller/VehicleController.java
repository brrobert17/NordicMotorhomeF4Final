package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.service.VehicleService;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class VehicleController {

    final
    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

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

    @PostMapping("vehicles/vehiclePage")
    public String filterVehicles(Model model,
                                 @Param("keyword") String keyword,
                                 Booking searchB) {
        String available = "Insert Date";
        List<Vehicle> vehiclesList = vehicleService.showFilteredVehicles(keyword,searchB);
        model.addAttribute("vehiclesList", vehiclesList);
        model.addAttribute("searchDateVehicle", new Booking());
        model.addAttribute("available", available);

        return "vehicles/vehiclePage";
    }

}
