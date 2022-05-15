package com.example.nordicmotorhomef4final.controller;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("vehicles/vehiclePage")
    public String showVehicles(Model model) {
//        String keyword = "";
//        String available = "Insert Date";
//        List<Vehicle> vehiclesList = vehicleService.findAll();
//        model.addAttribute("vehiclesList", vehiclesList);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("available", available);
//        model.addAttribute("searchDateVehicle", new Booking());
        return "vehicles/vehiclePage";
    }

//    @PostMapping("/vehicles/vehiclesPage")
//    public String filterVehicles(Model model,
//                                 @Param("keyword") String keyword, Booking searchB) {
////        System.out.println(searchB.getStartDate().toString()+" "+searchB.getEnd_date().toString());
//        String available = "Insert Date";
//        if (searchB.getStartDate() == null || searchB.getStartDate() == null) {
//            if (keyword == null) {
//                List<Vehicle> vehiclesList = vehicleService.findAll();
//                model.addAttribute("vehiclesList", vehiclesList);
//
//            } else {
//                System.out.println("the string is " + keyword);
//                List<Vehicle> vehiclesList = vehicleService.listAll(keyword);
//                model.addAttribute("vehiclesList", vehiclesList);
//            }
//        } else {
//            if (keyword == null) {
//                List<Vehicle> vehiclesList = vehicleService.findByDate(searchB.getStartDate().toString(), searchB.getEndDate().toString());
//                model.addAttribute("vehiclesList", vehiclesList);
//                available = "Available: "+searchB.getStartDate().toString()+searchB.getEndDate().toString();
//            } else {
//                List<Vehicle> vehiclesList = vehicleService.searchKeywordAndDates(keyword,searchB.getStartDate().toString(), searchB.getEndDate().toString());
//                model.addAttribute("vehiclesList", vehiclesList);
//                available = "Available: "+searchB.getStartDate().toString()+ " "+searchB.getEndDate().toString();
//                System.out.println("date+kw "+ keyword);
//            }
//        }
//        model.addAttribute("searchDateVehicle", new Booking());
//        model.addAttribute("available", available);
//
//        return "vehicles/vehiclesPage";
//    }

}
