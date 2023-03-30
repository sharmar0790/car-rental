package io.rental;

import io.utils.DatePeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarRentalTest {

    private static final Car CAR1 = new Car("VW", "Golf", "XX11 1UR", "B2", 90);
    private static final Car CAR2 = new Car("VW", "Passat", "XX12 2UR", "C1", 110);
    private static final Car CAR3 = new Car("VW", "Polo", "XX13 3UR", "A1", 65);
    private static final Car CAR4 = new Car("VW", "Polo", "XX14 4UR", "A1", 70);

    private static final Renter RENTER1 = new Renter("Hydrogen", "Joe", "HYDRO010190JX8NM", LocalDate.of(1990, 1, 1));
    private static final Renter RENTER2 = new Renter("Calcium", "Sam", "CALCI010203SX8NM", LocalDate.of(2003, 2, 1));
    private static final Renter RENTER3 = new Renter("Neon", "Maisy", "NEONN010398MX8NM", LocalDate.of(1998, 3, 1));
    private static final Renter RENTER4 = new Renter("Carbon", "Greta", "CARBO010497GX8NM", LocalDate.of(1997, 4, 1));

    @Test
    public void testListCarsAvailableToRentGivesMoreThanOneCar() {
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        Criteria criteria = new Criteria("Golf", "VW",null);
        List<Car> carsAvailable = carRentalCompany.matchingCars(criteria);

        assertThat(carsAvailable.size()).isEqualTo(1);
    }

    @Test
    public void testMatchingCarsAvailableToRentGivesMoreThanOneCar() {
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        Criteria criteria = new Criteria("Golf", "VW",null);
        List<Car> carsAvailable = carRentalCompany.matchingCars(criteria);

        assertThat(carsAvailable.size()).isEqualTo(1);
    }

    @Test
    public void testRentOneCar() {
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        Criteria criteria = new Criteria("Golf", "VW",null);
        List<Car> carsAvailable = carRentalCompany.matchingCars(criteria);
        assertThat(carsAvailable.size()).isGreaterThan(0);
        int carsAvailableSize = carsAvailable.size();
        Car car = carsAvailable.get(0);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2023, 03, 28), LocalDate.of(2023, 03, 30));
        carRentalCompany.rentCar(RENTER1, car, datePeriod);

        List<Car> carsAvailableAfterRenting = carRentalCompany.matchingCars(criteria);
        assertThat(carsAvailableAfterRenting.size()).isEqualTo(carsAvailableSize);

        double bill = carRentalCompany.calculateRentCarBill(car);
        assertThat(bill).isEqualTo(car.getCostPerDay() * 2);
    }

    @Test
    public void testRentSameCarWithTwoRentersWithOverLappingDatePeriod() {
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        Criteria criteria = new Criteria("Golf", "VW",null);
        List<Car> carsAvailable = carRentalCompany.matchingCars(criteria);
        assertThat(carsAvailable.size()).isGreaterThan(0);
        int carsAvailableSize = carsAvailable.size();
        Car car = carsAvailable.get(0);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2023, 03, 28),
                LocalDate.of(2023, 03, 30));
        carRentalCompany.rentCar(RENTER1, car, datePeriod);

        List<Car> carsAvailableAfterRenting = carRentalCompany.matchingCars(criteria);
        assertThat(carsAvailableAfterRenting.size()).isEqualTo(carsAvailableSize);

        double bill = carRentalCompany.calculateRentCarBill(car);
        assertThat(bill).isEqualTo(car.getCostPerDay() * 2);

        DatePeriod datePeriod2 = new DatePeriod(LocalDate.of(2023, 03, 29),
                LocalDate.of(2023, 04, 02));
        assertThrows(IllegalArgumentException.class, () -> carRentalCompany.rentCar(RENTER2, car, datePeriod2));
    }

    @Test
    public void testReturnCar() {
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        Criteria criteria = new Criteria("Golf", "VW",null);
        List<Car> carsAvailable = carRentalCompany.matchingCars(criteria);
        assertThat(carsAvailable.size()).isGreaterThan(0);
        int carsAvailableSize = carsAvailable.size();
        Car car = carsAvailable.get(0);

        DatePeriod datePeriod = new DatePeriod(LocalDate.of(2023, 03, 28), LocalDate.of(2023, 03, 30));
        carRentalCompany.rentCar(RENTER1, car, datePeriod);

        List<Car> carsAvailableAfterRenting = carRentalCompany.matchingCars(criteria);
        assertThat(carsAvailableAfterRenting.size()).isEqualTo(carsAvailableSize);

        double bill = carRentalCompany.calculateReturnCarBill(car, LocalDate.of(2023, 04, 02));
        assertThat(bill).isEqualTo(car.getCostPerDay() * 2);
        carRentalCompany.returnCar(RENTER1, car);

        List<Car> carsAvailableAfterReturn = carRentalCompany.matchingCars(criteria);
        assertThat(carsAvailableAfterReturn.size()).isEqualTo(carsAvailableSize);
    }
}
