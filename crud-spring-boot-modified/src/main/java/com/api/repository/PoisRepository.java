package com.api.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.Pois;
import com.api.interf.PoisRepositoryCustom;


@Repository
public interface PoisRepository extends CrudRepository<Pois, Long> , PoisRepositoryCustom {
	
	/*@Query("SELECT p FROM Pois p WHERE p.origin = ?1 and p.product = ?2 and p.quantity = ?3 and p.price = ?4 and p.type = ?5 ")
	Pois findPoisByParam(String origin, String product, long quantity, Double price, String type);*/
	
	@Query(value = "SELECT * FROM Pois ORDER BY id \n-- #pageable\n", 
		   countQuery = "SELECT count(*) FROM Pois",
		   nativeQuery = true)
	Pois findAllByPage(Pageable pageable);

	

}
