package com.example.todo_app.controller;

import com.example.todo_app.model.Todo;
import com.example.todo_app.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
  private final TodoRepository todoRepository;

  public TodoController(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping
  public List<Todo> getAllTodos() {
    return (List<Todo>) todoRepository.findAll();
  }

  @GetMapping("/{id}")
  public Todo getTodoById(@PathVariable long id) {
    return todoRepository.findById(id).orElse(null);
  }

  @PostMapping
  public Todo createTodo(@RequestBody Todo todo) {
    return todoRepository.save(todo);
  }

  @PutMapping("/{id}")
  public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
    return todoRepository.findById(id).map(todo -> {
      todo.setTitle(todo.getTitle());
      todo.setFinished(updatedTodo.isFinished());
      return todoRepository.save(todo);
    }).orElse(null);
  }

  @DeleteMapping("/{id}")
  public void deleteTodo(@PathVariable long id) {
    todoRepository.deleteById(id);
  }
}
