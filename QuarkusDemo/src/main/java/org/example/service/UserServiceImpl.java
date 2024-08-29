package org.example.service;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.example.dao.UserRepository;
import org.example.model.User;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.listAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean createUser(User user) {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(user.getUsername()) == null) {
            // 保存用户
            userRepository.persist(user);
            return true;
        }else{
            return false;
        }

    }



    @Override
    @Transactional
    public boolean updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            userRepository.persist(existingUser);
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        boolean isDeleted = userRepository.deleteById(id);
        return isDeleted;
    }
}
