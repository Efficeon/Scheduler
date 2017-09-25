package com.gtedx.repositories;

import com.gtedx.entities.JobEntity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobsRepository extends CrudRepository<JobEntity, Integer> {
    JobEntity findByJobId(String jobId);
}
