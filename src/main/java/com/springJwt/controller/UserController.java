package com.springJwt.controller;


import com.springJwt.model.Login;
import com.springJwt.model.UserModel;
import com.springJwt.serviceImpl.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    // create (register) end-point

    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody UserModel userModel){
        UserModel user=userServiceImpl.register(userModel);
        if(user!=null){
            return ResponseEntity.ok("registered");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not registered");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody Login login){
        Authentication authentication=userServiceImpl.login(login);
        if(authentication!=null){
            return ResponseEntity.ok(authentication);
        }
        else{
            return null;
        }
    }

}
