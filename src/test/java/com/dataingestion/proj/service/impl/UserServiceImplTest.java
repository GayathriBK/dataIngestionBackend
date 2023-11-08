package com.dataingestion.proj.service.impl;

import com.dataingestion.proj.model.User;
import com.dataingestion.proj.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testValidateUser() {
        // Create a user for testing
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        // Mock the behavior of the UserRepository's validate method
        Mockito.when(userRepository.validate(user)).thenReturn(true);

        // Test the validate method of the UserService
        boolean result = userService.validate(user);

        // Assert that the result is true (or as per your expected behavior)
        assertTrue(result);
    }

    @Test
    public void testAddUser() {
        // Create a user for testing
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        // Mock the behavior of the UserRepository's addUser method
        Mockito.when(userRepository.addUser(user)).thenReturn(true);

        // Test the addUser method of the UserService
        boolean result = userService.addUser(user);

        // Assert that the result is true (or as per your expected behavior)
        assertTrue(result);
    }
}

