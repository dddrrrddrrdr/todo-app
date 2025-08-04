package com.example.todo_app.dao.impl;

import com.example.todo_app.domain.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskDaoImplTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private TaskDaoImpl underTest;

  @Test
  public void testThatCreateTaskGeneratesCorrectSql() {
    Task task = new Task(1L, "Unit Test", 1L);
    underTest.create(task);

    verify(jdbcTemplate).update(eq("INSERT INTO tasks (task_id, title, owner_id) VALUES (?, ?, ?)"),
        eq(1L), eq("Unit Test"), eq(1L));
  }

  @Test
  public void testThatFindOneGeneratesCorrectSql() {
    underTest.findOne(1L);

    verify(jdbcTemplate).query(
        eq("SELECT task_id, title, owner_id FROM tasks WHERE task_id = ? LIMIT 1"),
        ArgumentMatchers.<TaskDaoImpl.TaskRowMapper>any(), eq(1L));
  }

  @Test
  public void testThatFindManyGeneratesCorrectSql() {
    underTest.findAll();
    verify(jdbcTemplate).query(eq("SELECT task_id, title, owner_id FROM tasks"),
        ArgumentMatchers.<TaskDaoImpl.TaskRowMapper>any());
  }

  @Test
  public void testThatUpdateGeneratesCorrectSql() {
    //User owner = new User(1L, "John", "example@gmail.com", "user");
    Task task = new Task(1L, "Unit Test", 1L);
    underTest.update(task.getId(), task);

    verify(jdbcTemplate).update(
        "UPDATE tasks SET task_id = ?, title = ?, owner_id = ? WHERE task_id = ?", 1L, "Unit Test",
        1L, 1L);
  }

  @Test
  public void testThatDeleteGeneratesCorrectSql() {
    underTest.delete(1L);
    verify(jdbcTemplate).update("DELETE FROM tasks WHERE task_id = ?", 1L);
  }
}
