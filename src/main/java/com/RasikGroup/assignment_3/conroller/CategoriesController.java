package com.RasikGroup.assignment_3.conroller;


import com.RasikGroup.assignment_3.entity.CategoryEntity;
import com.RasikGroup.assignment_3.entity.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
