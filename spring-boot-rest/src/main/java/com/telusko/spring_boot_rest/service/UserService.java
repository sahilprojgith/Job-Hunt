package com.telusko.spring_boot_rest.service;


import com.telusko.spring_boot_rest.model.User;
import com.telusko.spring_boot_rest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepo repo;

//    @Autowired
//    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);//spring ignores the new object-if autowired.

    @Autowired
    private BCryptPasswordEncoder encoder;

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12); // don't use @bean in Service use in Securityconfig.
//    }

    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }


}
