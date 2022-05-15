package com.example.nordicmotorhomef4final.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(columnDefinition = "INT AUTO_INCREMENT")
    private Integer customerId;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String firstName;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String lastName;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer phoneNumber;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String licenseNumber;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String cLicense;

    @Column(columnDefinition = "DATE NOT NULL")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}

