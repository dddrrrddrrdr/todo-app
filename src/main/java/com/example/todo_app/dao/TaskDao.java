package com.example.todo_app.dao;

import com.example.todo_app.domain.Task;

import java.util.*;

public interface TaskDao {

  void create(Task task);

  Optional<Task> findOne(Long id);

  List<Task> findAll();

  void update(Long id, Task task);

  void delete(Long id);
}
