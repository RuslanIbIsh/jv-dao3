package com.iri.dao3.db;

import com.iri.dao3.model.Car;
import com.iri.dao3.model.Driver;
import com.iri.dao3.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    public static final List<Car> cars = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    private static long driverId = 0;
    private static long carId = 0;
    private static long manufacturerId = 0;

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturerId);
        manufacturers.add(manufacturer);
        return manufacturer;
    }

    public static Car addCar(Car car) {
        car.setId(++carId);
        cars.add(car);
        return car;
    }

    public static Driver addDriver(Driver driver) {
        driver.setId(++driverId);
        drivers.add(driver);
        return driver;
    }
}
