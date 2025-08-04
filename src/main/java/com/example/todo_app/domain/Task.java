package com.example.todo_app.domain;

import java.util.Objects;

public class Task {
  private Long id;
  private String title;
  private Long ownerId;

  public Task() {
  }

  public Task(Long id, String title, Long ownerId) {
    this.id = id;
    this.title = title;
    this.ownerId = ownerId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getOwner_id() {
    return ownerId;
  }

  public void setOwner_id(Long ownerId) {
    this.ownerId = ownerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Task task = (Task) o;

    return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(
        ownerId, task.ownerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, ownerId);
  }
}
