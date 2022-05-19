package com.example.nordicmotorhomef4final.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "registration_plate", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
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

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", vehicle=" + vehicle +
                ", customer=" + customer +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
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
