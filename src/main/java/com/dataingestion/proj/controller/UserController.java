package com.dataingestion.proj.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dataingestion.proj.model.User;
import com.dataingestion.proj.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
 
    //This post request is used to validate the user name and password of the user
    @PostMapping("/validate")
    public boolean validateUser(@RequestBody User user) throws IOException {
    	return userService.validate(user);
    }
    
    //This post request is used to add an user  
    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) throws IOException {
    	return userService.addUser(user);
    }   

}
