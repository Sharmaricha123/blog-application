/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blog.services;

import com.blog.payloads.UserDto;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface UserService {
    
     UserDto createUser(UserDto user);
     UserDto updateUser(UserDto user,Integer userId);
     UserDto getUserById(Integer userId);
     List<UserDto> getAllUsers();
     void deleteUser(Integer userId);
     
    
}
