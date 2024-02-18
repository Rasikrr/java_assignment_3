package com.RasikGroup.assignment_3.entity.repository;

import com.RasikGroup.assignment_3.entity.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<JobEntity, Long> {

}
