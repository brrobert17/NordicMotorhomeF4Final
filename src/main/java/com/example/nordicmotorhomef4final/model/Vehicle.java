package com.example.nordicmotorhomef4final.model;

import javax.persistence.*;
import java.util.List;

//Class that models the vehicles object in the database

@Entity//
//In order to define an entity, you must create a class that is annotated with the @Entity annotation.
// The @Entity annotation is a marker annotation, which is used to discover persistent entities.
// https://www.infoworld.com/article/3373652/java-persistence-with-jpa-and-hibernate-part-1-entities-and-relationships.html

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
    //Here we define the "connection" between the vehicle object and the Booking object
    //One vehicle object appears in many bookings
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Booking> bookings;

//    Getters
    public String getRegistrationPlate() {
        return registrationPlate;
    }
    public List<Booking> getBookings() {
        return bookings;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public String getcLicense() {
        return cLicense;
    }
    public Double getTotalKm() {
        return totalKm;
    }

//    Setters
    public void setModel(String model) {
        this.model = model;
    }
    public void setTotalKm(Double totalKm) {
        this.totalKm = totalKm;
    }
    public void setcLicense(String cLicense) {
        this.cLicense = cLicense;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }


    @Override
    public String toString() {
        if (cLicense.equals("Yes")){
            return "Van "+ registrationPlate + " " + brand +" " + model + ", cap " + capacity + ", full Lic.";}
        else
            return "Van "+ registrationPlate + " " + brand +" " + model + ", cap " + capacity;}
    }

