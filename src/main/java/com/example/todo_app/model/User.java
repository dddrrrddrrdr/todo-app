package com.example.todo_app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class User {

  public User() {

  }

  @Id
  @GeneratedValue
  private Long userId;

  private String username;

  private String role;

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Todo> todos = new ArrayList<>();

  public List<Todo> getTodos() {
    return todos;
  }

  public void setTodos(List<Todo> todos) {
    this.todos = todos;
  }

}
