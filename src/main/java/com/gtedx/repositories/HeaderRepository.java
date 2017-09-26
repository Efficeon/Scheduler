package com.gtedx.repositories;

import com.gtedx.entities.RequestHeaderEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lion on 26.09.17.
 */
public interface HeaderRepository extends CrudRepository<RequestHeaderEntity, Integer> {
    RequestHeaderEntity findByHeaderId(String headerId);
}
