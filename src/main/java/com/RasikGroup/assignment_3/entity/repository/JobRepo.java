package com.RasikGroup.assignment_3.entity.repository;

import com.RasikGroup.assignment_3.entity.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepo extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findAllByAppliedById(Long userId);
    List<JobEntity> findAllByCreatedById(Long id);
}
