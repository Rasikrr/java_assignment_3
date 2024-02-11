package com.RasikGroup.assignment_3.entity.repository;

import com.RasikGroup.assignment_3.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface JobRepo extends JpaRepository<JobEntity, Long> {

}
