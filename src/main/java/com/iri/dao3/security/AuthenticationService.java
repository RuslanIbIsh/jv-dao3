package com.iri.dao3.security;

import com.iri.dao3.exception.AuthenticationException;
import com.iri.dao3.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
