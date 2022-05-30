package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.repo.VehicleRepo;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

// we need this to inform spring that the business logig is here
@Service
public class VehicleService {
    //    Uses the vehicles repository
    @Autowired
    private VehicleRepo vehicleRepo;

    //    ListAll: returns a List of Vehicles objects that contain the keyword
    //    either with bookings and/or search string (keyword) or a full List
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

    //   Shows all Vehicles
    public List<Vehicle> showAllVehicles() {
        return vehicleRepo.findAll();
    }

//    Saves a vehicle:
//    We are using a RedirectAttribute so we can show a Success/Failure Message
    @Transactional
    public RedirectAttributes saveVehicle(Vehicle vehicle, RedirectAttributes redirectAttributes) {
        if (vehicle.getcLicense() == null || vehicle.getBrand() == null || vehicle.getCapacity() == null || vehicle.getModel() == null) {
            return redirectAttributes.addFlashAttribute("alert", "Missing Fields");
        }
        if (vehicleRepo.searchByRegistrationPlate(vehicle.getRegistrationPlate()) == null) {
            vehicleRepo.save(vehicle);
            return redirectAttributes.addFlashAttribute("message", "Vehicle: " + vehicle.getRegistrationPlate() + " was saved to the database");
        } else {
            return redirectAttributes.addFlashAttribute("alert", "Vehicle: " + vehicle.getRegistrationPlate() + " already exists");
        }
    }
    @Transactional
    public RedirectAttributes editVehicle(Vehicle vehicle, RedirectAttributes redirectAttributes) {
        if (vehicle.getcLicense() == null || vehicle.getBrand() == null || vehicle.getCapacity() == null || vehicle.getModel() == null) {
            return redirectAttributes.addFlashAttribute("alert", "Missing Fields");
        } else {
            vehicleRepo.save(vehicle);
            return redirectAttributes.addFlashAttribute("message", "Vehicle: " + vehicle.getRegistrationPlate() + " was edited in the database");
        }
    }


//    Deletes a Vehicle in the repository
    @Transactional
    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepo.delete(vehicle);
    }

    //    Simple get vehicle from registrationPlate
    public Vehicle getVehicleById(String registrationPlate) {
        return vehicleRepo.searchByRegistrationPlate(registrationPlate);
    }


}
