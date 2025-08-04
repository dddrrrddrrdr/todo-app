package com.example.todo_app.dao.impl;

import com.example.todo_app.dao.UserDao;
import com.example.todo_app.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskDaoImplIntegrationTest {

  private UserDao userDao;
  private TaskDaoImpl underTest;

  @Autowired
  public TaskDaoImplIntegrationTest(UserDao userDao, TaskDaoImpl underTest) {
    this.userDao = userDao;
    this.underTest = underTest;
  }

  @Test
  public void testThatTaskCanBeCreatedAndRecalled() {
    User user = new User(1L, "John", "example@gamil.com", "user");
    userDao.create(user);
    Task task = new Task(1L, "Integration Test", 1L);
    task.setOwner_id(user.getId());
    underTest.create(task);
    Optional<Task> result = underTest.findOne(task.getId());
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(task);
  }

  @Test
  public void testThatMultipleTasksCanBeCreatedAndRecalled() {
    User userA = new User(1L, "John", "example@gmail.com", "user");
    userDao.create(userA);
    Task taskA = new Task(1L, "Integration Test", 1L);
    taskA.setOwner_id(userA.getId());
    underTest.create(taskA);

    User userB = new User(2L, "Anna", "example1@gmail.com", "admin");
    userDao.create(userB);
    Task taskB = new Task(2L, "Integration Test", 2L);
    taskB.setOwner_id(userB.getId());
    underTest.create(taskB);

    User userC = new User(3L, "Tom", "example2@gmail.com", "user");
    userDao.create(userC);
    Task taskC = new Task(3L, "Integration Test", 3L);
    taskC.setOwner_id(userC.getId());
    underTest.create(taskC);

    List<Task> result = underTest.findAll();
    assertThat(result).hasSize(3);
    assertThat(result).containsExactly(taskA, taskB, taskC);
  }

  @Test
  public void TestThatTaskCanBeUpdated() {
    User user = new User(1L, "John", "example@gmail.com", "user");
    userDao.create(user);
    Task task = new Task(1L, "Integration Test", 1L);
    task.setOwner_id(user.getId());
    underTest.create(task);
    task.setTitle("Updated Title");
    underTest.update(task.getId(), task);
    Optional<Task> result = underTest.findOne(task.getId());
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(task);
  }

  @Test
  public void TestThatTaskCanBeDeleted() {
    User user = new User(1L, "John", "example@gmail.com", "user");
    userDao.create(user);
    Task task = new Task(1L, "Integration Test", 1L);
    task.setOwner_id(user.getId());
    underTest.create(task);
    underTest.delete(task.getId());
    Optional<Task> result = underTest.findOne(task.getId());
    assertThat(result).isEmpty();
  }
}
