package com.example.nordicmotorhomef4final.service;

import com.example.nordicmotorhomef4final.model.Booking;
import com.example.nordicmotorhomef4final.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public List<Booking> showAllBookings() {
        return bookingRepo.findAll();
    }
    @Transactional
    public void saveBooking(Booking booking) {
        bookingRepo.save(booking);
    }
    @Transactional
    public void editBooking(Booking booking) {
        bookingRepo.save(booking);
    }

    public Booking findBookingById(Integer id) {
        return bookingRepo.getBookingById(id);
    }

    /*public List<Booking> showFilteredBookings(String keyword, Booking searchBooking) {
        if (searchBooking.getStartDate() == null || searchBooking.getEndDate() == null) {
            if (keyword == null) {
                return bookingRepo.findAll();
            } else {
                return bookingRepo.searchBookingByKeyword(keyword);
            }
        } else {
            if (keyword == null) {
                return bookingRepo.searchBookingByDate(searchBooking.getStartDate(), searchBooking.getEndDate());
            } else {
                return bookingRepo.searchBookingByDateAndKeyword(searchBooking.getStartDate(), searchBooking.getEndDate(), keyword);
            }
        }
    }*/

    //show List of booking with keyword
    public List<Booking> showAllBookingKeyword(String keyword) {
        return bookingRepo.searchBookingByKeyword(keyword);
    }
    //@Transactional is used to make sure that the database is updated
    @Transactional
    //delete a booking by id
    public void deleteBookingById(Integer id) throws CustomerNotFoundException {
        Long count = bookingRepo.countByBookingId(id);
        if (count== null || count ==0) {
            throw new CustomerNotFoundException("Could not find any customers with the ID: " + id);
        }
        bookingRepo.deleteById(id);
    }
//calulate the price of a booking by the number of days
    public int calculateDays(Booking booking) {
        LocalDate startDate = booking.getStartDate();
        LocalDate endDate = booking.getEndDate();

        Period period = Period.between(startDate, endDate);

        return Math.abs(period.getDays());
    }

    //calculate the total price of a booking
    public double calculateTotalPrice(Booking booking) {
        double totalPrice = 0;
        double pricePerDay = 0;

        if (booking.getVehicle().getCapacity() == 2) {
            pricePerDay = 120;
        } else if (booking.getVehicle().getCapacity() == 4) {
            pricePerDay = 200;
        } else { // booking.getVehicle().getCapacity() == 6
            pricePerDay = 240;
        }

        int days = calculateDays(booking);
        totalPrice = pricePerDay * days;

        return totalPrice;
    }



    //return all booking id which is have same registration plate

    public List<Booking> checkRegisrtationPlateIsAvailable(Integer bookingId) {
        //find all same registration plate and cheack if the date is colliding
        List<Booking> bookings = bookingRepo.findAll();
        Booking plateOfCar = bookingRepo.getBookingById(bookingId);
        for (Booking b : bookings) {
            if (b.getVehicle().getRegistrationPlate().equals(plateOfCar.getVehicle().getRegistrationPlate())) {
                return bookingRepo.findAllByVehicle_RegistrationPlate(plateOfCar.getVehicle().getRegistrationPlate());

            }
        }
        return null;
    }


    //make  sure data of booking is not colliding with specific booking with same customer and vehicle id
    public boolean checkCollision(Integer bookingId) {
        List<Booking> cars = checkRegisrtationPlateIsAvailable(bookingId);
        if (cars != null) {
            for (Booking b : cars) {
                if (b.getBookingId() != bookingId) {
                    if (b.getStartDate().isBefore(bookingRepo.getBookingById(bookingId).getEndDate()) && b.getEndDate().isAfter(bookingRepo.getBookingById(bookingId).getStartDate())) {
                        return false;
                    }
                }
            }

        }
        return true;

    }
}








