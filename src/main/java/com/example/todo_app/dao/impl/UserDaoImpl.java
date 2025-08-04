package com.example.todo_app.dao.impl;

import com.example.todo_app.dao.UserDao;
import com.example.todo_app.domain.User;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class UserDaoImpl implements UserDao {

  private final JdbcTemplate jdbcTemplate;

  public UserDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void create(User user) {
    jdbcTemplate.update("INSERT INTO users (id, name, email, role) VALUES (?, ?, ?, ?)",
        user.getId(), user.getName(), user.getEmail(), user.getRole());
  }

  @Override
  public Optional<User> findOne(Long id) {
    List<User> result =
        jdbcTemplate.query("SELECT id, name, email, role FROM users WHERE id = ? LIMIT 1",
            new UserRowMapper(), id);
    return result.stream().findFirst();
  }

  public static class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
          rs.getString("role"));
    }
  }

  @Override
  public List<User> findAll() {
    return jdbcTemplate.query("SELECT id, name, email, role FROM users", new UserRowMapper());
  }

  @Override
  public void update(Long id, User user) {
    jdbcTemplate.update("UPDATE users SET id = ?, name = ?, email = ?, role = ? WHERE id = ?",
        user.getId(), user.getName(), user.getEmail(), user.getRole(), id);
  }

  @Override
  public void delete(Long id) {
    jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
  }
}
