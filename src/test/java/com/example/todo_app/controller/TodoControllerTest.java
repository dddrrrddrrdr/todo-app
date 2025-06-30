package com.example.todo_app.controller;

import com.example.todo_app.model.Todo;
import com.example.todo_app.repository.TodoRepository;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoControllerTest {

  private TodoRepository todoRepository;
  private TodoController todoController;

  @BeforeEach
  void setUp() {
    todoRepository = mock(TodoRepository.class);
    todoController = new TodoController(todoRepository);
  }

  @Test
  void testGetAllTodos() {
    Todo todo1 = new Todo();
    Todo todo2 = new Todo();
    when(todoRepository.findAll()).thenReturn(Arrays.asList(todo1, todo2));

    List<Todo> todos = todoController.getAllTodos();

    assertEquals(2, todos.size());
    verify(todoRepository).findAll();
  }

  @Test
  void testGetTodoById_Found() {
    Todo todo = new Todo();
    todo.setId(1L);
    when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

    Todo result = todoController.getTodoById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    verify(todoRepository).findById(1L);
  }

  @Test
  void testGetTodoById_NotFound() {
    when(todoRepository.findById(2L)).thenReturn(Optional.empty());

    Todo result = todoController.getTodoById(2L);

    assertNull(result);
    verify(todoRepository).findById(2L);
  }

  @Test
  void testCreateTodo() {
    Todo todo = new Todo();
    todo.setTitle("Test");
    when(todoRepository.save(todo)).thenReturn(todo);

    Todo result = todoController.createTodo(todo);

    assertEquals("Test", result.getTitle());
    verify(todoRepository).save(todo);
  }

  @Test
  void testUpdateTodo_Found() {
    Todo existing = new Todo();
    existing.setId(1L);
    existing.setTitle("Old");
    existing.setFinished(false);

    Todo updated = new Todo();
    updated.setTitle("New");
    updated.setFinished(true);

    when(todoRepository.findById(1L)).thenReturn(Optional.of(existing));
    when(todoRepository.save(any(Todo.class))).thenReturn(existing);

    Todo result = todoController.updateTodo(1L, updated);

    assertNotNull(result);
    assertEquals("New", updated.getTitle());
    assertTrue(result.isFinished());
    verify(todoRepository).findById(1L);
    verify(todoRepository).save(existing);
  }

  @Test
  void testUpdateTodo_NotFound() {
    Todo updated = new Todo();
    updated.setTitle("New");
    updated.setFinished(true);

    when(todoRepository.findById(2L)).thenReturn(Optional.empty());

    Todo result = todoController.updateTodo(2L, updated);

    assertNull(result);
    verify(todoRepository).findById(2L);
    verify(todoRepository, never()).save(any());
  }

  @Test
  void testDeleteTodo() {
    todoController.deleteTodo(1L);
    verify(todoRepository).deleteById(1L);
  }
}
