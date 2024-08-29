package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(Long id);
    boolean createUser(User user);
    boolean updateUser(Long id, User user);
    boolean deleteUser(Long id);
}
