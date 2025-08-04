package com.example.todo_app.dao.impl;

import com.example.todo_app.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDaoImplTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private UserDaoImpl underTest;

  @Test
  public void testThatCreateUserGeneratesCorrectSql() {
    User user = new User(1L, "John", "example@gmail.com", "user");
    underTest.create(user);

    verify(jdbcTemplate).update(eq("INSERT INTO users (id, name, email, role) VALUES (?, ?, ?, ?)"),
        eq(1L), eq("John"), eq("example@gmail.com"), eq("user"));
  }

  @Test
  public void testThatFindOneGeneratesCorrectSql() {
    underTest.findOne(1L);

    verify(jdbcTemplate).query(eq("SELECT id, name, email, role FROM users WHERE id = ? LIMIT 1"),
        ArgumentMatchers.<UserDaoImpl.UserRowMapper>any(), eq(1L));
  }

  @Test
  public void testThatFindManyGeneratesCorrectSql() {
    underTest.findAll();
    verify(jdbcTemplate).query(eq("SELECT id, name, email, role FROM users"),
        ArgumentMatchers.<UserDaoImpl.UserRowMapper>any());
  }

  @Test
  public void testThatUpdateGeneratesCorrectSql() {
    User user = new User(1L, "John", "example@gmail.com", "user");
    underTest.update(user.getId(), user);
    verify(jdbcTemplate).update(
        "UPDATE users SET id = ?, name = ?, email = ?, role = ? WHERE id = ?", 1L, "John",
        "example@gmail.com", "user", 1L);
  }

  @Test
  public void testThatDeleteGeneratesCorrectSql() {
    underTest.delete(1L);
    verify(jdbcTemplate).update("DELETE FROM users WHERE id = ?", 1L);
  }
}
