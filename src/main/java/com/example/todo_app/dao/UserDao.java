package com.example.todo_app.dao;

import com.example.todo_app.domain.User;

import java.util.*;

public interface UserDao {

  void create(User user);

  Optional<User> findOne(Long id);

  List<User> findAll();

  void update(Long id, User user);

  void delete(Long id);
}
