package com.gtedx.controllers;

import com.gtedx.entities.JobEntity;
import com.gtedx.entities.Request;
import com.gtedx.entities.Response;
import com.gtedx.exception.EntityNotFoundException;
import com.gtedx.repositories.JobsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lion on 25.09.17.
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobsRepository repository;

    public JobController(JobsRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody Request<JobEntity> request) {
        JobEntity jobEntity = request.getBody();
        repository.save(jobEntity);
        return new Response("job_id" + jobEntity.getId());
    }

    @GetMapping("/{job_id}")
    public Response read(@PathVariable("job_id") String id) {
        JobEntity jobEntity  = repository.findOne(Integer.valueOf(id));

        Response response = new Response(jobEntity);
        if (jobEntity == null) throw new EntityNotFoundException(String.format("job %s not found", id));
        return response;
    }

    @DeleteMapping("/{job_id}")
    public Response delete(@PathVariable("job_id") String id) {
        JobEntity jobEntity  = repository.findOne(Integer.valueOf(id));
        if (jobEntity == null) throw new EntityNotFoundException(String.format("template %s not found", id));
        repository.delete(Integer.valueOf(id));
        return new Response("200", String.format("job %s deleted successfully", id));
    }
}
