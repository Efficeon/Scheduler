package com.gtedx.controllers;

import com.gtedx.entities.*;
import com.gtedx.exception.EntityNotFoundException;
import com.gtedx.repositories.HeaderRepository;
import com.gtedx.repositories.JobsRepository;
import com.gtedx.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lion on 25.09.17.
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobsRepository jobsRepository;
    private final TaskRepository taskRepository;
    private final HeaderRepository headerRepository;
    public JobController(JobsRepository jobsRepository,
                         TaskRepository taskRepository,
                         HeaderRepository headerRepository) {
        this.jobsRepository = jobsRepository;
        this.taskRepository = taskRepository;
        this.headerRepository = headerRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody Request<JobEntity> request) {
        JobEntity jobEntity = request.getBody();
        headerRepository.save(jobEntity.getTask().getHeader());
        taskRepository.save(jobEntity.getTask());
        jobsRepository.save(jobEntity);
        return new Response(new JobResponse(jobEntity.getJobId()));
    }

    @GetMapping("/{job_id}")
    public Response read(@PathVariable("job_id") String id) {
        JobEntity jobEntity  = jobsRepository.findOne(Integer.valueOf(id));

        Response response = new Response(jobEntity);
        if (jobEntity == null) throw new EntityNotFoundException(String.format("job %s not found", id));
        return response;
    }

    @DeleteMapping("/{job_id}")
    public Response delete(@PathVariable("job_id") String id) {
        JobEntity jobEntity  = jobsRepository.findOne(Integer.valueOf(id));
        if (jobEntity == null) throw new EntityNotFoundException(String.format("job %s not found", id));
        jobsRepository.delete(Integer.valueOf(id));
        return new Response("200", String.format("job %s deleted successfully", id));
    }
}
