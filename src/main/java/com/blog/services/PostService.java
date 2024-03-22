/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blog.services;


import com.blog.payloads.PostDto;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface PostService {
    
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    
    PostDto updatePost(PostDto postDto,Integer postId);
    
    PostDto getPostById(Integer postId);
    
    List<PostDto> getAllPost();
    
    void deletePost(Integer postId);
    
//    get all posts by category
    List<PostDto> getPostByCategory(Integer categoryId);
    
//    get all posts by user
    List<PostDto> getPostByUser(Integer userId);
    
//    search posts
    List<PostDto> searchPosts(String keyword);
    
}
