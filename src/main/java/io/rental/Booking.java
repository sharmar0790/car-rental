package io.rental;

import io.utils.DatePeriod;

public class Booking {
    private final Renter renter;
    private final Car car;
    private final DatePeriod period;

    public Booking(Renter renter, Car car, DatePeriod period) {
        this.renter = renter;
        this.car = car;
        this.period = period;
    }

    public DatePeriod getPeriod() {
        return period;
    }

    public Renter getRenter() {
        return renter;
    }
}
