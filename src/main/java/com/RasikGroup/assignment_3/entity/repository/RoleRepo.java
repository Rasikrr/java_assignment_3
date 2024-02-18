package com.RasikGroup.assignment_3.entity.repository;

import com.RasikGroup.assignment_3.entity.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
}
