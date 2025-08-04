package com.example.todo_app.domain;

import java.util.Objects;

public class User {
  private Long id;
  private String name;
  private String email;
  private String role;

  public User() {
  }

  public User(Long id, String name, String email, String role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return Objects.equals(role, user.role) && Objects.equals(id, user.id) && Objects.equals(name,
        user.name) && Objects.equals(email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, role);
  }
}
