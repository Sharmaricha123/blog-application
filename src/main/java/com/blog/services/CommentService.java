/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blog.services;

import com.blog.payloads.CommentDto;

/**
 *
 * @author Asus
 */
public interface CommentService {
    
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
    
}
