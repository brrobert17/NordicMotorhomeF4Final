package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Customer;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void saveVehicle(Vehicle vehicle){ vehicleRepo.save(vehicle);}

    public Vehicle getVehicleById(String registrationPlate) throws CustomerNotFoundException {
        Vehicle vehicle = vehicleRepo.searchByRegistrationPlate(registrationPlate);
        if (vehicle != null) {
            return vehicle;
        }
        throw new CustomerNotFoundException("Could not find any vehicle with Registration Plate: " + registrationPlate);
    }
}