package com.RasikGroup.assignment_3.entity.service;

import com.RasikGroup.assignment_3.entity.CategoryEntity;
import com.RasikGroup.assignment_3.entity.JobEntity;
import com.RasikGroup.assignment_3.entity.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<CategoryEntity> getAll(){
        List<CategoryEntity> categoryEntities = (List<CategoryEntity>) categoryRepo.findAll();
        return categoryEntities;
    }
}
