package com.example.todo_app.integration;

import com.example.todo_app.model.Todo;
import com.example.todo_app.repository.TodoRepository;
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
public class TodoControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TodoRepository todoRepository;

  @Test
  void getAllTodos() throws Exception {
    Todo todo = new Todo();
    todo.setTitle("Integration Test Todo");
    todoRepository.save(todo);

    mockMvc.perform(get("/api/todos")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void createTodo() throws Exception {
    String json = "{\"title\":\"Integration Test Todo\",\"finished\":\"false\"}";

    mockMvc.perform(post("/api/todos").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Integration Test Todo"));
  }

  @Test
  void updateTodo() throws Exception {
    Todo todo = new Todo();
    todo.setTitle("Old Title");
    todo.setFinished(false);
    todo = todoRepository.save(todo);

    String json = "{\"title\":\"Updated Title\",\"finished\":true}";

    mockMvc.perform(
            put("/api/todos/" + todo.getId()).contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Updated Title"))
        .andExpect(jsonPath("$.finished").value(true));
  }

  @Test
  void deleteTodo() throws Exception {
    Todo todo = new Todo();
    todo.setTitle("To Delete");
    todo = todoRepository.save(todo);

    mockMvc.perform(delete("/api/todos/" + todo.getId())).andExpect(status().isOk());
  }

}
