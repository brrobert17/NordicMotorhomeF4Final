package com.example.nordicmotorhomef4final.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
//use spring data Jpa @Entity annotation to create a table in the database
@Entity
//@Table means that the table name is "booking"
@Table(name = "bookings")
public class Booking {
    //@Id means that this is the primary key of the table
    @Id
    //@GeneratedValue means that the value is generated by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
//@ManyToOne means it a foreign key in sql
    @ManyToOne
    @JoinColumn(name = "registration_plate", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    //@JoinColumn means that the foreign key is called "customerId"
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    // getters
    public int getBookingId() {
        return bookingId;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public Customer getCustomer() {
        return customer;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    // setters
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    ///*
    @Override
    public String toString() {
        return bookingId + " " + vehicle.getRegistrationPlate() + " " + customer.getCustomerId() + " " + startDate + " " + endDate;
    }
    //*/
}

/***************************************************************************

 THIS CLASS GENERATES THE FOLLOWING SQL DDL QUERY TO CREATE THE TABLE bookings

 CREATE TABLE bookings (
 booking_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 registration_plate VARCHAR(255) NOT NULL,
 customer_id INT NOT NULL,
 start_date date NOT NULL,
 end_date date NOT NULL,
 FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
 FOREIGN KEY (registration_plate) REFERENCES vehicles(registration_plate)
 );

 ***************************************************************************/
