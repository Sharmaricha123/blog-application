/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blog.controllers;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;
import java.util.List;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {
       List<PostDto> posts= this.postService.getPostByUser(userId);
       return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
    {
       List<PostDto> posts= this.postService.getPostByCategory(categoryId);
       return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    
    
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(@RequestParam(value = "pageNumber",defaultValue="10",required=false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue="1",required=false)Integer pageSize){
      List<PostDto> allPosts=  this.postService.getAllPost(pageNumber,pageSize);
      return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
    }
    
    
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
       PostDto post= this.postService.getPostById(postId);
       return new ResponseEntity<>(post,HttpStatus.OK);
    }
    
    
    @DeleteMapping("posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
    }
    
    
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
    {
       PostDto updatePost= this.postService.updatePost(postDto, postId);
       return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }
}
