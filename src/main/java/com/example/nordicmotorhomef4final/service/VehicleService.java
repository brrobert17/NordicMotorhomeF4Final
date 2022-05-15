package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Vehicle;
import com.example.nordicmotorhomef4final.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
//    Uses the vehicles repository
    private VehicleRepo repo;

    //    ListAll: returns a List of Vehicles objects that contain the keyword
    public List<Vehicle> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public List<Vehicle> findAll() {
        return repo.findAll();
    }

    public List<Vehicle> findByDate(String start, String  end){
        return repo.searchAvailable(start,end);
    }

    public List<Vehicle> searchKeywordAndDates(String keyword, String start, String  end){
        return repo.searchKeywordANDDates(keyword,start,end);
    }

}