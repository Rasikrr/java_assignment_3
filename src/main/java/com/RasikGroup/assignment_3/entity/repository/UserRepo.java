package com.RasikGroup.assignment_3.entity.repository;

import com.RasikGroup.assignment_3.entity.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
