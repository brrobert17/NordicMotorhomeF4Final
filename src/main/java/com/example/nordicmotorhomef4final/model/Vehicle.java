package com.example.nordicmotorhomef4final.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @Column(columnDefinition = "VARCHAR(255)")
    private String registrationPlate;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer capacity;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String brand;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String model;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String cLicense;

    @Column(columnDefinition = "DOUBLE NOT NULL")
    private Double totalKm;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}