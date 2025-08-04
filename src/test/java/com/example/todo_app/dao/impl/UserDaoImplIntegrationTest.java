package com.example.todo_app.dao.impl;

import com.example.todo_app.domain.User;
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
public class UserDaoImplIntegrationTest {

  private UserDaoImpl underTest;

  @Autowired
  public UserDaoImplIntegrationTest(UserDaoImpl underTest) {
    this.underTest = underTest;
  }

  @Test
  public void testThatUserCanBeCreatedAndRecalled() {
    User user = new User(1L, "John", "example@gmail.com", "user");
    underTest.create(user);
    Optional<User> result = underTest.findOne(user.getId());
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(user);
  }

  @Test
  public void testThatMultipleUsersCanBeCreatedAndRecalled() {
    User userA = new User(1L, "John", "example@gmail.com", "user");
    underTest.create(userA);
    User userB = new User(2L, "Anna", "example1@gmail.com", "admin");
    underTest.create(userB);
    User userC = new User(3L, "Tom", "example2@gmail.com", "user");
    underTest.create(userC);

    List<User> result = underTest.findAll();
    assertThat(result).hasSize(3);
    assertThat(result).containsExactly(userA, userB, userC);
  }

  @Test
  public void testThatUserCanBeUpdatedAndRecalled() {
    User user = new User(1L, "John", "example@gmail.com", "user");
    underTest.create(user);
    user.setName("Updated name");
    underTest.update(user.getId(), user);
    Optional<User> result = underTest.findOne(user.getId());
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(user);
  }

  @Test
  public void testThatUserCanBeDeletedAndRecalled() {
    User user = new User(1L, "John", "example@gmail.com", "user");
    underTest.create(user);
    underTest.delete(user.getId());
    Optional<User> result = underTest.findOne(user.getId());
    assertThat(result).isEmpty();
  }
}
