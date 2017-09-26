package com.gtedx.repositories;

import com.gtedx.entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lion on 26.09.17.
 */
public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {
    TaskEntity findByTaskId(int id);
}
