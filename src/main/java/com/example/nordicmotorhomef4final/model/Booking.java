package com.example.nordicmotorhomef4final.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(columnDefinition = "INT AUTO_INCREMENT ")
    private Long bookingId;

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
}
