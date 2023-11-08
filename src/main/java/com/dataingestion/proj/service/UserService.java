package com.dataingestion.proj.service;

import com.dataingestion.proj.model.User;

public interface UserService {
	boolean validate(User user);
	
	boolean addUser(User user);
}
