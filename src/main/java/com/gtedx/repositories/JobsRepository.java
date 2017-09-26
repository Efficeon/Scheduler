package com.gtedx.repositories;

import com.gtedx.entities.JobEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lion on 26.09.17.
 */
public interface JobsRepository extends CrudRepository<JobEntity, Integer> {
    JobEntity findByJobId(String jobId);
}
