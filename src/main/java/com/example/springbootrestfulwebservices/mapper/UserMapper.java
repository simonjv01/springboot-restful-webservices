package com.example.springbootrestfulwebservices.mapper;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;

public class UserMapper {


    public static UserDto maptToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }
}
