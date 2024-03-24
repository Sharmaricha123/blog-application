/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blog.repositories;

import com.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Asus
 */
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    
}
