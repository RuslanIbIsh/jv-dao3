package com.iri.dao3.dao;

import com.iri.dao3.model.Driver;
import java.util.Optional;

public interface DriverDao extends GenericDao<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
