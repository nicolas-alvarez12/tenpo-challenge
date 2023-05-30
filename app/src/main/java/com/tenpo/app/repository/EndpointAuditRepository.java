package com.tenpo.app.repository;

import com.tenpo.app.model.EndpointAuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface EndpointAuditRepository extends CrudRepository<EndpointAuditEntity, Integer> {

    Page<EndpointAuditEntity> findAll(Pageable pageable);


}
