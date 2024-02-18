package com.RasikGroup.assignment_3.entity.service;

import com.RasikGroup.assignment_3.entity.entities.CategoryEntity;
import com.RasikGroup.assignment_3.entity.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<CategoryEntity> getAll(){
        List<CategoryEntity> categoryEntities = (List<CategoryEntity>) categoryRepo.findAll();
        return categoryEntities;
    }

    public Optional<CategoryEntity> getById(Long id){
        return categoryRepo.findById(id);
    }


}
