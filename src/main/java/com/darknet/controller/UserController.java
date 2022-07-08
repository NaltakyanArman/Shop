package com.darknet.controller;

import com.darknet.model.User;
import com.darknet.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UsersRepository usersRepository;


    @PostMapping("/regUser")
    public String registerUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        usersRepository.save(user);
        return ("redirect:/itemslist");
    }

    @GetMapping("/regUser")
    public String regUser() {
        return "regUser";
    }

    @GetMapping("/logIn")
    public String loginPage() {
        return "logIn";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


}
