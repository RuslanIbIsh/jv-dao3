package com.iri.dao3.dao.impl;

import com.iri.dao3.dao.CarDao;
import com.iri.dao3.db.Storage;
import com.iri.dao3.lib.Dao;
import com.iri.dao3.model.Car;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        return Storage.addCar(car);
    }

    @Override
    public Optional<Car> get(Long id) {
        return Storage.cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        IntStream.range(0, Storage.cars.size())
                .filter(index -> Storage.cars.get(index).getId().equals(car.getId()))
                .forEach(index -> Storage.cars.set(index, car));
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(car -> car.getId().equals(id));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return Storage.cars.stream()
                .filter(
                        car -> car.getDrivers().stream()
                                .anyMatch(driver -> driver.getId().equals(driverId))
                )
                .collect(Collectors.toList());
    }
}
