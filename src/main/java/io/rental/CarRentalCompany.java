package io.rental;

import io.utils.DatePeriod;
import io.utils.DatePeriodUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRentalCompany {
    private final List<Car> cars = new ArrayList<>();
    private final Map<String, List<Booking>> bookingsMap = new HashMap<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> matchingCars(Criteria criteria) {
        return criteria.findMatchingCar(cars);
    }

    public void rentCar(Renter renter, Car car, DatePeriod datePeriod) {
        List<Booking> bookingList = new ArrayList<>();
        Booking booking;
        if (!bookingsMap.containsKey(car.getRegistrationNumber())) {
            booking = new Booking(renter, car, datePeriod);
            bookingList.add(booking);
            bookingsMap.put(car.getRegistrationNumber(), bookingList);
        } else {
            bookingList = bookingsMap.get(car.getRegistrationNumber());
            if (!DatePeriodUtil.areOverlapping(bookingList.get(bookingList.size() - 1).getPeriod(), datePeriod)) {
                booking = new Booking(renter, car, datePeriod);
                bookingList.add(booking);
                bookingsMap.put(car.getRegistrationNumber(), bookingList);
            } else {
                throw new IllegalArgumentException("Overlapping time period. . !!");
            }
        }
    }

    public void returnCar(Renter renter, Car car) {
        List<Booking> bookings = bookingsMap.get(car.getRegistrationNumber());
        bookings.removeIf(
                booking -> booking.getRenter().getDrivingLicenseNumber().equals(renter.getDrivingLicenseNumber()));
        bookingsMap.put(car.getRegistrationNumber(), bookings);
    }

    public double calculateReturnCarBill(Car car, LocalDate instantDate) {
        List<Booking> bookingList = bookingsMap.get(car.getRegistrationNumber());
        if (DatePeriodUtil.isInPeriod(instantDate, bookingList.get(0).getPeriod())) {
            return 0;
        } else {
            DatePeriod datePeriod = bookingList.get(0).getPeriod();
            int hours =
                    datePeriod.getEnd().getDayOfMonth() - datePeriod.getStart().getDayOfMonth();
            return hours * car.getCostPerDay();
        }
    }

    public double calculateRentCarBill(Car car) {
        List<Booking> bookingList = bookingsMap.get(car.getRegistrationNumber());
        DatePeriod datePeriod = bookingList.get(0).getPeriod();
        int hours = datePeriod.getEnd().getDayOfMonth() - datePeriod.getStart().getDayOfMonth();
        return hours * car.getCostPerDay();
    }
}
