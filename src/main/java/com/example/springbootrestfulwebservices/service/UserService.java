package com.example.springbootrestfulwebservices.service;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    User getUserByID(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
