package com.api.interf;

import java.util.List;

import com.api.domain.Pois;


public interface PoisRepositoryCustom {

	Pois getPoisBySearch(String name, Double valmin, Double valmax, Integer page);
	Pois getAllPoisByPage(Integer page);
	List<Pois> getPoisByList(String name, Double valmin, Double valmax, Integer page);

}
