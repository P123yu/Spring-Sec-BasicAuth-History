package com.springJwt.serviceImpl;


import com.springJwt.model.Login;
import com.springJwt.model.UserModel;
import com.springJwt.repository.UserRepository;
import com.springJwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel register(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        UserModel user=userRepository.save(userModel);
        return user;
    }


    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Authentication login(Login login) {
        //System.out.println(login.getEmail()+" "+login.getPassword());
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()));
        return authentication;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username).orElse(null);
//    }
}
