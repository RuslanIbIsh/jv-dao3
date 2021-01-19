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
        Manufacturer manufacturerOne = new Manufacturer("Volvo", "Shwiden");
        manufacturerService.create(manufacturerOne);

        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        Driver driverOne = new Driver("Leo", "8383");
        Driver driverTwo = new Driver("Sanny", "1111");
        Driver driverThree = new Driver("Goll", "2221");
        driverService.create(driverOne);
        driverService.create(driverTwo);
        driverService.create(driverThree);
        driverService.get(driverOne.getId());
        driverService.getAll();
        Driver updatedDriver = driverService.get(driverOne.getId());
        updatedDriver.setName("Leonard");
        driverService.update(updatedDriver);
        driverService.delete(driverOne.getId());

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car carOne = new Car("sedanLux", manufacturerOne);
        carService.create(carOne);
        carService.addDriverToCar(driverOne, carOne);
        carService.addDriverToCar(driverTwo, carOne);
        carService.addDriverToCar(driverThree, carOne);
        Car carTwo = new Car("sedanLux", manufacturer);
        carService.create(carTwo);
        carService.addDriverToCar(driverOne, carTwo);
        carService.addDriverToCar(driverTwo, carTwo);
        carService.get(carOne.getId());
        carService.getAll();
        carService.getAllByDriver(driverThree.getId());
        Car updatedCar = new Car("cupe", manufacturer);
        updatedCar.setId(carTwo.getId());
        carService.update(updatedCar);
        carService.delete(carOne.getId());
        carService.removeDriverFromCar(driverOne, carOne);
    }
}
