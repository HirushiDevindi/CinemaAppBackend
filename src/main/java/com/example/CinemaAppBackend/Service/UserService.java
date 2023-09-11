package com.example.CinemaAppBackend.Service;

import com.example.CinemaAppBackend.Entity.User;
import com.example.CinemaAppBackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override //spring security
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");


        final User user =  userRepo.findByUsername(username);
        if(user ==null){
            throw new UsernameNotFoundException("User not found with username : "+ username);
        }

        //return (UserDetails) user;
        return new UserUserDetails(user);
//        return (UserDetails) userRepo.findByUsername(username).orElseThrow(() ->
//                new UsernameNotFoundException("User not found with username : "+ username));



    }









}
