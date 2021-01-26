package com.iri.dao3.service;

import com.iri.dao3.model.Driver;
import java.util.Optional;

public interface DriverService extends GenericService<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
