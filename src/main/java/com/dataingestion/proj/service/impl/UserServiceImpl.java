package com.dataingestion.proj.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataingestion.proj.model.User;
import com.dataingestion.proj.repository.UserRepository;
import com.dataingestion.proj.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(User user){
    	return userRepository.validate(user);
    }
    
    @Override
    public boolean addUser(User user){
    	return userRepository.addUser(user);
    }
}
