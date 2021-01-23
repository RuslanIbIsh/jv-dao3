package com.iri.dao3.security;

import com.iri.dao3.dao.DriverDao;
import com.iri.dao3.exception.AuthenticationException;
import com.iri.dao3.lib.Inject;
import com.iri.dao3.lib.Service;
import com.iri.dao3.model.Driver;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driver = driverDao.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Incorrect login or password"));
        if (driver.getPassword().equals(password)) {
            return driver;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
