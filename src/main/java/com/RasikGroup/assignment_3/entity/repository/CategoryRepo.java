package com.RasikGroup.assignment_3.entity.repository;

import com.RasikGroup.assignment_3.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
}
