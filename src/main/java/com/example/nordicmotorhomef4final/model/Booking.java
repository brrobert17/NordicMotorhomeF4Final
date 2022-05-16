package com.example.nordicmotorhomef4final.model;

import javax.persistence.*;

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
    private String startDate;

    @Column(name = "end_date",nullable = false)
    private String endDate;

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
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
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
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
