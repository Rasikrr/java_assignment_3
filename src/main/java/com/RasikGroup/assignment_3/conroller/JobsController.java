package com.RasikGroup.assignment_3.conroller;

import com.RasikGroup.assignment_3.dtos.JobRequest;
import com.RasikGroup.assignment_3.dtos.CreateJobRequest;
import com.RasikGroup.assignment_3.dtos.UpdateJobRequest;
import com.RasikGroup.assignment_3.entity.entities.JobEntity;
import com.RasikGroup.assignment_3.entity.service.JobService;
import com.RasikGroup.assignment_3.exception.JobNotFoundException;
import com.RasikGroup.assignment_3.exception.JobValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobsController {

    @Autowired
    private JobService jobService;
    @CrossOrigin
    @GetMapping("/all")
    public @ResponseBody List<JobEntity> getAll(){
        return jobService.getAll();
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity getJob(@PathVariable Long id){
        try{
            return ResponseEntity.ok(jobService.getById(id));
        } catch (JobNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @CrossOrigin
    @PostMapping("/postjob")
    public ResponseEntity postJob(@RequestBody CreateJobRequest createJobRequest){
        try {
            jobService.createJob(createJobRequest);
            return ResponseEntity.ok().body("{\"message\": \"Vacancy was successfully created\"}");
        } catch (JobValidationException jobValidationException){
            return ResponseEntity.badRequest().body("{\"message\": \"Please check data\"}");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("{\"message\": \"Please check data\"}");
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.ok().body("{\"message\": \"Vacancy was successfully deleted\"}");
    }

    @CrossOrigin
    @PostMapping("/apply")
    public ResponseEntity applyJob(@RequestBody JobRequest jobRequest){
        jobService.applyJob(jobRequest);
        return ResponseEntity.ok().body("{\"message\": \"success\"}");
    }

    @CrossOrigin
    @GetMapping("/applied/{user_id}")
    public @ResponseBody List<JobEntity> getAllAppliedByUser(@PathVariable Long user_id){
        return jobService.getAppliedByUserId(user_id);
    }

    @CrossOrigin
    @DeleteMapping("/applied/delete")
    public ResponseEntity deleteAppliedJob(@RequestBody JobRequest jobRequest){
        jobService.deleteAppliedJob(jobRequest);
        return ResponseEntity.ok().body("{\"message\": \"success\"}");
    }

    @CrossOrigin
    @GetMapping("/posted/all/{id}")
    public @ResponseBody List<JobEntity> getAllPostedByUser(@PathVariable Long id){
        return jobService.getAllPostedById(id);
    }

    @CrossOrigin
    @PutMapping("/posted/update")
    public ResponseEntity updateJob(@RequestBody UpdateJobRequest updateJobRequest){
        try {
            jobService.updateJob(updateJobRequest);
            return ResponseEntity.ok().body("{\"message\": \"Vacancy was successfully created\"}");
        } catch (JobValidationException jobValidationException){
            return ResponseEntity.badRequest().body("{\"message\": \"Please check data\"}");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("{\"message\": \"Please check data\"}");
        }
    }

}
