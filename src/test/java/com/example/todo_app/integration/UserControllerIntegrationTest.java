package com.example.todo_app.integration;

import com.example.todo_app.model.User;
import com.example.todo_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @Test
  void createUser() throws Exception {
    String json = "{\"username\":\"Integration User\",\"todos\":[]}";

    mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk()).andExpect(jsonPath("$.username").value("Integration User"));
  }

  @Test
  void getAllUsers() throws Exception {
    User user = new User();
    user.setUsername("Integration User");
    userRepository.save(user);

    mockMvc.perform(get("/api/users")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void getUserById() throws Exception {
    User user = new User();
    user.setUsername("Integration User");
    user = userRepository.save(user);

    mockMvc.perform(get("/api/users/" + user.getUserId())).andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("Integration User"));
  }
}
