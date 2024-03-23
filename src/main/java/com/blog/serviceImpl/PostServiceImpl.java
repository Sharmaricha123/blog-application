/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blog.serviceImpl;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        
       User user= this.userRepository.findById(userId)
               .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
       
       Category category=this.categoryRepository.findById(categoryId)
               .orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
       
      Post post=  this.modelMapper.map(postDto, Post.class);
      post.setImageName("default.png");
      post.setAddedDate(new Date());
      post.setUser(user);
      post.setCategory(category);
      Post savedPost=this.postRepository.save(post);
      return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
         Post post= this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
         post.setTitle(postDto.getTitle());
         post.setContent(postDto.getContent());
//         post.setImageName(postDto.getImageName());
       Post updatedPost=  this.postRepository.save(post);
       return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
      Post post= this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
      return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
        
//        int pageSize=5;
//        int pageNumber=1;
//        
      Pageable p=PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePost= this.postRepository.findAll(p);
        List<Post> posts=pagePost.getContent();
       List<PostDto> postDtos=posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
       return postDtos;
    }

    @Override
    public void deletePost(Integer postId) {
       Post post= this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        List<Post> posts=this.postRepository.findByCategory(cat);
        List<PostDto> postDtos=posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
       User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
       List<Post> posts=this.postRepository.findByUser(user);
       
       List<PostDto> postDtos=posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
       return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
