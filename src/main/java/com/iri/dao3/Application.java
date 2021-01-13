package com.iri.dao3;

import com.iri.dao3.lib.Injector;
import com.iri.dao3.model.Car;
import com.iri.dao3.model.Driver;
import com.iri.dao3.model.Manufacturer;
import com.iri.dao3.service.CarService;
import com.iri.dao3.service.DriverService;
import com.iri.dao3.service.ManufacturerService;

public class Application {
    private static Injector injector = Injector.getInstance("com.iri.dao3");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService)
                injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturer = new Manufacturer("Honda", "Japan");
        manufacturerService.create(manufacturer);
        manufacturerService.get(manufacturer.getId());
        manufacturerService.getAll();
        Manufacturer updatedManufacturer = new Manufacturer("Toyota", "Turkey");
        updatedManufacturer.setId(manufacturer.getId());
        manufacturerService.update(updatedManufacturer);
        manufacturerService.delete(manufacturer.getId());

        DriverService driverService = (DriverService)
                injector.getInstance(DriverService.class);
        Driver driver = new Driver("John", "aa2021");
        driverService.create(driver);
        driverService.get(driver.getId());
        driverService.getAll();
        Driver updatedDriver = new Driver("John", "ka2021");
        updatedDriver.setId(driver.getId());
        driverService.update(updatedDriver);
        driverService.delete(driver.getId());

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car car = new Car("offroad", manufacturer);
        carService.create(car);
        carService.get(car.getId());
        carService.getAll();
        carService.addDriverToCar(driver, car);
        carService.removeDriverFromCar(driver, car);
        Car updatedCar = new Car("offroad", updatedManufacturer);
        updatedCar.setId(car.getId());
        carService.update(updatedCar);
        carService.delete(car.getId());
        carService.getAllByDriver(driver.getId());
    }
}
