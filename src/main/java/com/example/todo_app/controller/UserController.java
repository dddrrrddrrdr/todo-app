package com.example.todo_app.controller;

import com.example.todo_app.model.*;
import com.example.todo_app.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    for (Todo todo : user.getTodos()) {
      todo.setUser(user);
    }
    return userRepository.save(user);
  }

  @GetMapping
  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userRepository.findById(id).orElse(null);
  }

}
