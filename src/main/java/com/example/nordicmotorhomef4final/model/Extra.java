package com.example.nordicmotorhomef4final.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer extraId;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String type;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String quantity;

    @OneToMany(mappedBy = "extra")
    private List<Booking> bookings;
}
