package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.repo.VehicleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    //    Uses the vehicles repository
    private final VehicleRepo repo;

    public VehicleService(VehicleRepo repo) {
        this.repo = repo;
    }

    //    ListAll: returns a List of Vehicles objects that contain the keyword
    public List<Vehicle> showFilteredVehicles(String keyword, Booking searchBooking) {
        if (searchBooking.getStartDate() == null || searchBooking.getEndDate() == null) {
            if (keyword == null) {
                return repo.findAll();
            } else {
                return repo.searchForKeyword(keyword);
            }
        } else {
            if (keyword == null) {
                return repo.searchAvailable(searchBooking.getStartDate(), searchBooking.getEndDate());
            } else {
                return repo.searchKeywordAndDates(keyword,searchBooking.getStartDate(), searchBooking.getEndDate());
            }
        }
    }

    public List<Vehicle> showAllVehicles() {
        return repo.findAll();
    }

}