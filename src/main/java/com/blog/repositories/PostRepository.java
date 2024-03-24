/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blog.repositories;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Asus
 */
public interface PostRepository extends JpaRepository<Post, Integer>{
    
    List<Post> findByUser(User user);
    
    List<Post> findByCategory(Category category);
    
    List<Post> findByTitleContaining(String title);    
    
//    Same as above (alternative way)=findByTitleContaining
//    @Query("select p from Post p where p.title like :key")
//    List<Post> searchByTitle(@Param("key") String title);
}
