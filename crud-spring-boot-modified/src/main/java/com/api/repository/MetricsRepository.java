package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Metrics;
import com.api.interf.MetricsRepositoryCustom;

@Repository
public interface MetricsRepository extends CrudRepository<Metrics, Long> , MetricsRepositoryCustom {

	
	
	
}
