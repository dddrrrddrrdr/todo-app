package com.example.todo_app.model;


import jakarta.persistence.*;

@Entity
public class Todo {
  @Id
  @GeneratedValue
  private Long id;

  private String title;
  private boolean finished;

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

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }
}
