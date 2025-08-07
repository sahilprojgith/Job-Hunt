package com.sahil.spring_boot_rest.service;

import com.sahil.spring_boot_rest.model.JobPost;
import com.sahil.spring_boot_rest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    public JobRepo repo;

    public List<JobPost> getAllJobs() {
        return repo.findAll(); // FIXED
    }

    public void addJobPost(JobPost jobPost) {
        repo.save(jobPost);
    }

    public JobPost getJob(int postId) {
        return repo.findById(postId).orElse(new JobPost());
    }

    public void updateJob(JobPost jobPost) {
        repo.save(jobPost);
    }

    public void deleteJob(int postId) {
        repo.deleteById(postId);
    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword,keyword);
    }
}
