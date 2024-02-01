package com.example.springbootrestfulwebservices.service.impl;

import com.example.exceptions.ResourceNotFoundException;
import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;
import com.example.springbootrestfulwebservices.mapper.AutoUserMapper;
import com.example.springbootrestfulwebservices.mapper.UserMapper;
import com.example.springbootrestfulwebservices.repository.UserRepository;
import com.example.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert UserDto into User JPA Entity

       // User user1 = UserMapper.mapToUser(userDto);

        // User user1 = UserMapper.mapToUser(userDto);


        User user1 = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user1);

        // Convert user1 JPA entity to UserDto

        // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

//        UserDto savedUserDto = UserMapper.maptToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserByID(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId)
        );
        
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user)-> modelMapper.map(user, UserDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.maptToUserDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }

}
