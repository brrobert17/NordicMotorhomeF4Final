package com.example.nordicmotorhomef4final.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//This class/entity represents the database's "customers" table
@Entity
@Table(name = "customers")
public class Customer {

    // The "@Id" represents the column of the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    // The individual attributes marked by "@Column" are the equivalents of each column in the table
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

    // The "@OneToMany" annotation means that one "Customer" object can be part of many "Booking" objects
    // The meaning of CascadeType.ALL is that the persistence will propagate (cascade) all EntityManager operations:
    // (PERSIST, REMOVE, REFRESH, MERGE, DETACH) to the relating entities.
    // In practise: a "Booking" object has no meaning without a "Customer"
    // if I delete a "Customer", the related "Bookings" are deleted as well
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    // The application only uses the default empty constructor

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getcLicense() {
        return cLicense;
    }

    public void setcLicense(String cLicense) {
        this.cLicense = cLicense;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String miniString(){
        return firstName +", " + lastName + ", "+ phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", cLicense='" + cLicense + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bookings=" + bookings +
                '}';
    }
}


