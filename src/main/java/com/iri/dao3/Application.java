package com.iri.dao3;

import com.iri.dao3.lib.Injector;
import com.iri.dao3.model.Manufacturer;
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
    }
}
