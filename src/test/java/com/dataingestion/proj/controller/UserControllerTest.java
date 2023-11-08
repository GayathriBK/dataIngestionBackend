package com.dataingestion.proj.controller;


import com.dataingestion.proj.model.User;
import com.dataingestion.proj.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testValidateUser() throws Exception {
        User user = new User();
        // Set appropriate values for your test user

        when(userService.validate(user)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"testuser\", \"password\": \"testpassword\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        // Set appropriate values for your test user

        when(userService.addUser(user)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"testuser\", \"password\": \"testpassword\"}"))
                .andExpect(status().isOk());
    }
}

