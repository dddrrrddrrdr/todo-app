package com.example.todo_app.dao.impl;

import com.example.todo_app.dao.TaskDao;
import com.example.todo_app.domain.Task;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class TaskDaoImpl implements TaskDao {

  private final JdbcTemplate jdbcTemplate;

  public TaskDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void create(Task task) {
    jdbcTemplate.update("INSERT INTO tasks (task_id, title, owner_id) VALUES (?, ?, ?)",
        task.getId(), task.getTitle(), task.getOwner_id());
  }

  @Override
  public Optional<Task> findOne(Long task_id) {
    List<Task> results =
        jdbcTemplate.query("SELECT task_id, title, owner_id FROM tasks WHERE task_id = ? LIMIT 1",
            new TaskRowMapper(), task_id);
    return results.stream().findFirst();
  }

  public static class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Task(rs.getLong("task_id"), rs.getString("title"), rs.getLong("owner_id"));
    }
  }

  @Override
  public List<Task> findAll() {
    return jdbcTemplate.query("SELECT task_id, title, owner_id FROM tasks", new TaskRowMapper());
  }

  @Override
  public void update(Long id, Task task) {
    jdbcTemplate.update("UPDATE tasks SET task_id = ?, title = ?, owner_id = ? WHERE task_id = ?",
        task.getId(), task.getTitle(), task.getOwner_id(), id);
  }

  @Override
  public void delete(Long id) {
    jdbcTemplate.update("DELETE FROM tasks WHERE task_id = ?", id);
  }
}
