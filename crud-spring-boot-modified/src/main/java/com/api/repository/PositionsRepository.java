package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Positions;
import com.api.interf.PositionsRepositoryCustom;

@Repository
public interface PositionsRepository extends CrudRepository<Positions, Long> , PositionsRepositoryCustom {

}
