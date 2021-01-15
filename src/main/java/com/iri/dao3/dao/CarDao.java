package com.iri.dao3.dao;

import com.iri.dao3.model.Car;
import com.iri.dao3.model.Driver;
import java.util.List;

public interface CarDao extends GenericDao<Car, Long> {
    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

    List<Car> getAllByDriver(Long driverId);
}
