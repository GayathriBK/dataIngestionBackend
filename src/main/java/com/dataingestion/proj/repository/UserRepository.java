package com.dataingestion.proj.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.dataingestion.proj.model.User;


@Repository
public class UserRepository {
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Add user into the database
    public boolean validate(User user) {
        String sql = "SELECT password FROM users WHERE username=?";
        String pw = jdbcTemplate.queryForObject(sql, new Object[]{user.getUsername()}, String.class);
        if(pw != null && pw.equals(user.getPassword()))
        {
        	return true;
        }
        return false;
    }
    
 // Insert a new user into the database
    public boolean addUser(User user) {
    	String sql = "INSERT INTO users (username,password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(),user.getPassword());
        return true;
    }
}
