package com.iri.dao3.service;

import com.iri.dao3.model.Driver;
import java.util.List;

public interface DriverService {
    Driver create(Driver driver);

    Driver get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);
}
