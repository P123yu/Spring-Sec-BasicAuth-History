package com.springJwt.service;


import com.springJwt.model.Login;
import com.springJwt.model.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // create  (register)
    UserModel register(UserModel userModel);

    // login
    Authentication login(Login login);

}
