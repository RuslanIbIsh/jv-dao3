package com.iri.dao3.service.impl;

import com.iri.dao3.dao.DriverDao;
import com.iri.dao3.lib.Inject;
import com.iri.dao3.lib.Service;
import com.iri.dao3.model.Driver;
import com.iri.dao3.service.DriverService;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        return driverDao.create(driver);
    }

    @Override
    public Driver get(Long id) {
        return driverDao.get(id).get();
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        return driverDao.delete(id);
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        return driverDao.findByLogin(login);
    }
}
