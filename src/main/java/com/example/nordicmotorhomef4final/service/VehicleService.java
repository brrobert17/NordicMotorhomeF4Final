package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Service
public class VehicleService {
    //    Uses the vehicles repository
    @Autowired
    private VehicleRepo vehicleRepo;

    //    ListAll: returns a List of Vehicles objects that contain the keyword
    public List<Vehicle> showFilteredVehicles(String keyword, Booking searchBooking) {
        if (searchBooking.getStartDate() == null || searchBooking.getEndDate() == null) {
            if (keyword == null) {
                return vehicleRepo.findAll();
            } else {
                return vehicleRepo.searchForKeyword(keyword);
            }
        } else {
            if (keyword == null) {
                return vehicleRepo.searchAvailable(searchBooking.getStartDate(), searchBooking.getEndDate());
            } else {
                return vehicleRepo.searchKeywordAndDates(keyword, searchBooking.getStartDate(), searchBooking.getEndDate());
            }
        }
    }

    public List<Vehicle> showAllVehicles() {
        return vehicleRepo.findAll();
    }

    public List<String> brandList() {
        List<String> brandList = new ArrayList<String>(){};
        brandList.add("Brand");
        for (Vehicle v:vehicleRepo.findAll()
             ) {
            if (!brandList.contains(v.getBrand()))
            brandList.add(v.getBrand());
        }
        return brandList;
    }

    public RedirectAttributes saveVehicle(Vehicle vehicle, RedirectAttributes redirectAttributes){
        if(vehicle.getcLicense()== null || vehicle.getBrand()==null||vehicle.getCapacity()==null||vehicle.getModel()==null){
            return redirectAttributes.addFlashAttribute("alert", "Missing Fields");
        }
        if (vehicleRepo.searchByRegistrationPlate(vehicle.getRegistrationPlate()) == null) {
            vehicleRepo.save(vehicle);
            return redirectAttributes.addFlashAttribute("message", "Vehicle: " + vehicle.getRegistrationPlate() + " was saved to the database");
        } else {
            return redirectAttributes.addFlashAttribute("alert", "Vehicle: " + vehicle.getRegistrationPlate() + " already exists");
        }
    }

    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepo.delete(vehicle);
    }

    public Vehicle getVehicleById(String registrationPlate){
        return vehicleRepo.searchByRegistrationPlate(registrationPlate);
    }


}