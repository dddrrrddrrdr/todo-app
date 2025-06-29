package com.example.todo_app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

  @Test
  void todoTest() {
    User user = new User();
    Todo todo = new Todo();

    user.setUserId(1L);
    user.setUsername("john");
    user.setRole("admin");

    todo.setId(2L);
    todo.setTitle("Test");
    todo.setFinished(true);
    todo.setUser(user);

    assertEquals(2L, todo.getId());
    assertEquals("Test", todo.getTitle());
    assertTrue(todo.isFinished());
    assertEquals(user, todo.getUser());
  }
}
