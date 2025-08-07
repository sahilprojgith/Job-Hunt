package com.sahil.spring_boot_rest;

import com.sahil.spring_boot_rest.service.JobService;
import com.sahil.spring_boot_rest.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Allow React
public class JobRestController {

    @Autowired
    private JobService jobService;

    @GetMapping(value="jobPosts",produces = {"application/json"}) //only returns JSON.
    public List<JobPost> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){
        return jobService.getJob(postId);
    }

    @GetMapping("jobPost/keyword/{keyword}")
    public List<JobPost> searchByKeyWord(@PathVariable("keyword") String keyword){
        return jobService.search(keyword);
    }

    @PostMapping("jobPost")
    public void addJob(@RequestBody JobPost jobPost){
        jobService.addJobPost(jobPost);
    }


    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        jobService.updateJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId){
        jobService.deleteJob(postId);
        return "Deleted";
    }


}
