package com.RasikGroup.assignment_3.conroller;

import com.RasikGroup.assignment_3.entity.JobEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity getJob(@PathVariable Long id){
        try{
            return ResponseEntity.ok(jobService.getById(id));
        } catch (JobNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/postjob")
    public ResponseEntity postJob(@RequestBody JobEntity job){
        jobService.createJob(job);
        return ResponseEntity.ok("Vacancy was successfully created");
    }


}
