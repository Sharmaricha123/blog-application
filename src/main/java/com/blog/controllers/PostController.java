/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blog.controllers;

import com.blog.config.AppConstants;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;
import java.util.List;
import lombok.Value;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    
//    @Value("${project.image}")
    private String path;
    
    @Autowired
    private FileService fileService;
    
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
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required=false)String sortDir
    ){
      PostResponse postResponse=  this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
      return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
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
    
    
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
       List<PostDto> result= this.postService.searchPosts(keywords);
       return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    
//    @PostMapping("/post/image/upload/{postId}")
//    public ResponseEntity<ImageResponse> uploadPostImage(
//    @RequestParam("image") MultipartFile image
//    ){
//        
//    }
}

