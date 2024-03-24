/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blog.serviceImpl;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.PostRepository;
import com.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }
    
    @Override
    public void deleteComment(Integer commentId) {
        
        Comment com = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        this.commentRepository.delete(com);
    }
    
}
