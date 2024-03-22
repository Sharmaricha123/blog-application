/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blog.services;

import com.blog.payloads.CategoryDto;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface CategoryService {
    
     CategoryDto createCategory(CategoryDto categoryDto);
     
     CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
     
     void  deleteCategory(Integer categoryId);
     
     CategoryDto getCategory(Integer categoryId);
     
     List<CategoryDto> getCategories();
    

    
}
