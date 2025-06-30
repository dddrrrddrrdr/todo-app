package com.example.todo_app.controller;

import com.example.todo_app.model.*;
import com.example.todo_app.repository.UserRepository;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

  private UserRepository userRepository;
  private UserController userController;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);
    userController = new UserController(userRepository);
  }

  @Test
  void testCreateUser() {
    User user = new User();
    Todo todo = new Todo();
    user.setTodos(new ArrayList<>(List.of(todo)));

    when(userRepository.save(user)).thenReturn(user);

    User result = userController.createUser(user);

    assertNotNull(result);
    assertEquals(user, result);
    assertEquals(user, todo.getUser());
    verify(userRepository).save(user);
  }

  @Test
  void testGetAllUsers() {
    User user1 = new User();
    User user2 = new User();
    when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

    List<User> users = userController.getAllUsers();

    assertEquals(2, users.size());
    verify(userRepository).findAll();
  }

  @Test
  void testGetUserById_Found() {
    User user = new User();
    user.setUserId(1L);
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    User result = userController.getUserById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getUserId());
    verify(userRepository).findById(1L);
  }

  @Test
  void testGetUserById_NotFound() {
    when(userRepository.findById(2L)).thenReturn(Optional.empty());

    User result = userController.getUserById(2L);

    assertNull(result);
    verify(userRepository).findById(2L);
  }
}
