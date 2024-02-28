package com.RasikGroup.assignment_3.entity.service;

import com.RasikGroup.assignment_3.dtos.JobRequest;
import com.RasikGroup.assignment_3.dtos.CreateJobRequest;
import com.RasikGroup.assignment_3.entity.entities.CategoryEntity;
import com.RasikGroup.assignment_3.entity.entities.JobEntity;
import com.RasikGroup.assignment_3.entity.entities.UserEntity;
import com.RasikGroup.assignment_3.entity.repository.CategoryRepo;
import com.RasikGroup.assignment_3.entity.repository.JobRepo;
import com.RasikGroup.assignment_3.entity.repository.UserRepo;
import com.RasikGroup.assignment_3.exception.JobNotFoundException;
import com.RasikGroup.assignment_3.exception.JobValidationException;
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

    @Autowired
    private UserRepo userRepo;

    public List<JobEntity> getAll(){
        List<JobEntity> jobEntities = (List<JobEntity>) jobRepo.findAll();
        return jobEntities;
    }

    public JobEntity getById(Long id) throws JobNotFoundException {
        Optional<JobEntity> jobEntities = jobRepo.findById(id);
        JobEntity jobEntity = jobEntities.orElse(null);
        if(jobEntity == null){
            throw new JobNotFoundException("Job with this id not found");
        }
        return jobEntity;
    }

    public void createJob(CreateJobRequest createJobRequest) throws JobValidationException {
        Long category_id = createJobRequest.getCategory();
        if(category_id == 0){
            throw new JobValidationException("Invalid category");
        }
        CategoryEntity category = categoryRepo.getById(createJobRequest.getCategory());
        JobEntity jobEntity = new JobEntity();
        jobEntity.setCategory(category);
        jobEntity.setLocation(createJobRequest.getLocation());
        jobEntity.setTitle(createJobRequest.getTitle());
        jobEntity.setSalary_range(createJobRequest.getSalary_range());
        jobEntity.setRequirements(createJobRequest.getRequirements());
        jobEntity.setDescription(createJobRequest.getDescription());
        UserEntity user = userRepo.getById(createJobRequest.getUser_id());
        jobEntity.setCreatedBy(user);
        jobRepo.save(jobEntity);

    }

    public void deleteJob(Long id){
        jobRepo.deleteById(id);
    }

    public void applyJob(JobRequest jobRequest){
        JobEntity jobEntity = jobRepo.getById(jobRequest.getJob_id());
        UserEntity user = userRepo.getById(jobRequest.getUser_id());
        jobEntity.getAppliedBy().add(user);
        jobRepo.save(jobEntity);
    }

    public List<JobEntity> getAppliedByUserId(Long id){
        List<JobEntity> appliedByUser = (List<JobEntity>) jobRepo.findAllByAppliedById(id);
        return appliedByUser;
    }

    public void deleteAppliedJob(JobRequest jobRequest){
        JobEntity job = jobRepo.getById(jobRequest.getJob_id());
        UserEntity user = userRepo.getById(jobRequest.getUser_id());
        job.getAppliedBy().remove(user);
        jobRepo.save(job);
    }

    public List<JobEntity> getAllPostedById(Long id){
        List<JobEntity> postedJobs = jobRepo.findAllByCreatedById(id);
        return postedJobs;
    }

}
