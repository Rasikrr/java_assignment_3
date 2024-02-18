package com.RasikGroup.assignment_3.conroller;


import com.RasikGroup.assignment_3.entity.entities.CategoryEntity;
import com.RasikGroup.assignment_3.entity.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @CrossOrigin
    @GetMapping("/all")
    public @ResponseBody List<CategoryEntity> getAllCategories(){
        return categoriesService.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoriesService.getById(id));
    }
}
