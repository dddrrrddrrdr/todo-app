package com.example.todo_app.model;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

  @Test
  void userTest() {
    User user = new User();
    Todo todo = new Todo();

    todo.setTitle("Finish testing");
    todo.setUser(user);

    List<Todo> todos = new ArrayList<>();
    todos.add(todo);

    user.setUserId(1L);
    user.setUsername("john");
    user.setRole("admin");
    user.setTodos(todos);

    assertEquals(1L, user.getUserId());
    assertEquals("john", user.getUsername());
    assertEquals("admin", user.getRole());
    assertEquals(todos, user.getTodos());

  }
}
