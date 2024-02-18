package com.RasikGroup.assignment_3.conroller;

import com.RasikGroup.assignment_3.dtos.CreateJobRequest;
import com.RasikGroup.assignment_3.entity.entities.JobEntity;
import com.RasikGroup.assignment_3.entity.service.JobService;
import com.RasikGroup.assignment_3.exception.JobNotFoundException;
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
        jobService.createJob(createJobRequest);
        return ResponseEntity.ok().body("{\"message\": \"Vacancy was successfully created\"}");
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.ok().body("{\"message\": \"Vacancy was successfully deleted\"}");
    }


}
