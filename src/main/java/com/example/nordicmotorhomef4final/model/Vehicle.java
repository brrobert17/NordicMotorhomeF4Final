package com.example.nordicmotorhomef4final.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @Column(columnDefinition = "VARCHAR(255)")
    private String registrationPlate;

    @Column(columnDefinition = "INT NOT NULL")
    private int capacity;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String brand;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String model;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String cLicense;

    @Column(columnDefinition = "DOUBLE NOT NULL")
    private double totalKm;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getcLicense() {
        return cLicense;
    }

    public void setcLicense(String cLicense) {
        this.cLicense = cLicense;
    }

    public Double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(Double totalKm) {
        this.totalKm = totalKm;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}