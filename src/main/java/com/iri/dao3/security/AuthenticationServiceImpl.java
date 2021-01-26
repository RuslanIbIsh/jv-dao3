package com.iri.dao3.security;

import com.iri.dao3.dao.DriverDao;
import com.iri.dao3.exception.AuthenticationException;
import com.iri.dao3.lib.Inject;
import com.iri.dao3.lib.Service;
import com.iri.dao3.model.Driver;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driverFromDB = driverDao.findByLogin(login);
        if (driverFromDB.isPresent() && driverFromDB.get().getPassword().equals(password)) {
            return driverFromDB.get();
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
