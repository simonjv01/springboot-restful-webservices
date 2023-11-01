package com.example.springbootrestfulwebservices.controller;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;
import com.example.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    // build create User REST API
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userService.getUserByID(userId);
        return new  ResponseEntity<>(user, HttpStatus.OK);
    }

    // build getAll user REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // build update user REST API
    // http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody UserDto user) {
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // build delete user REST API
    // http://localhost:8080/api/users/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User succussfully deleted!" , HttpStatus.OK);
    }




}
