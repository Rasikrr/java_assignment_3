package com.RasikGroup.assignment_3.entity.service;

import com.RasikGroup.assignment_3.dtos.CreateJobRequest;
import com.RasikGroup.assignment_3.entity.entities.CategoryEntity;
import com.RasikGroup.assignment_3.entity.entities.JobEntity;
import com.RasikGroup.assignment_3.entity.repository.CategoryRepo;
import com.RasikGroup.assignment_3.entity.repository.JobRepo;
import com.RasikGroup.assignment_3.exception.JobNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public List<JobEntity> getAll(){
        List<JobEntity> userEntities = (List<JobEntity>) jobRepo.findAll();
        return userEntities;
    }

    public JobEntity getById(Long id) throws JobNotFoundException {
        Optional<JobEntity> jobEntities = jobRepo.findById(id);
        JobEntity jobEntity = jobEntities.orElse(null);
        if(jobEntity == null){
            throw new JobNotFoundException("Job with this id not found");
        }
        return jobEntity;
    }

    public void createJob(CreateJobRequest createJobRequest){
        CategoryEntity category = categoryRepo.getById(createJobRequest.getCategory());
        JobEntity jobEntity = new JobEntity();
        jobEntity.setCategory(category);
        jobEntity.setLocation(createJobRequest.getLocation());
        jobEntity.setTitle(createJobRequest.getTitle());
        jobEntity.setSalary_range(createJobRequest.getSalary_range());
        jobEntity.setRequirements(createJobRequest.getRequirements());
        jobEntity.setDescription(createJobRequest.getDescription());
        jobRepo.save(jobEntity);

    }

    public void deleteJob(Long id){
        jobRepo.deleteById(id);
    }


}
