/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blog.serviceImpl;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public UserDto createUser(UserDto userDto) {
        
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepository.save(user);
        return this.userToDto(savedUser);
        
    }
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        
        User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser=this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        
        User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        
     List<User> users=   this.userRepository.findAll();
      List<UserDto>userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
      return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        this.userRepository.delete(user);
       
    }
    
    
    public User dtoToUser(UserDto userDto)
    {
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        
        User user=this.modelMapper.map(userDto, User.class);
        return user;
        
    }
    
    public UserDto userToDto(User user)
    {
//      UserDto userDto=new UserDto();
//      userDto.setId(user.getId());
//      userDto.setName(user.getName());
//      userDto.setEmail(user.getEmail());
//      userDto.setAbout(user.getAbout());
//      userDto.setPassword(user.getPassword());
        
        UserDto userDto=this.modelMapper.map(user, UserDto.class);
      return userDto;
    }
    
}
