/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blog.controllers;

import com.blog.payloads.PostDto;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,@PathVariable Integer userId,@PathVariable Integer categoryId)
    {
        
       PostDto createdPost= this.postService.createPost(postDto, userId, categoryId);
       return new ResponseEntity<>(createdPost,HttpStatus.CREATED);
        
    }
    
}
