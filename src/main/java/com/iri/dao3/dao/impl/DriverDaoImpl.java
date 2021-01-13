package com.iri.dao3.dao.impl;

import com.iri.dao3.dao.DriverDao;
import com.iri.dao3.db.Storage;
import com.iri.dao3.lib.Dao;
import com.iri.dao3.model.Driver;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        return Storage.addDriver(driver);
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Storage.drivers.stream()
                .filter(driver -> driver.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }

    @Override
    public Driver update(Driver driver) {
        IntStream.range(0, Storage.drivers.size())
                .filter(index -> Storage.drivers.get(index).getId().equals(driver.getId()))
                .forEach(index -> Storage.drivers.set(index, driver));
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.removeIf(driver -> driver.getId().equals(id));
    }
}
