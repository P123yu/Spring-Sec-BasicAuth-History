package com.springJwt.service;


import com.springJwt.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // create  (register)
    UserModel register(UserModel userModel);

}
