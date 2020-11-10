package com.api.interf;

import java.util.Date;
import java.util.List;

import com.api.domain.Metrics;


public interface MetricsRepositoryCustom {
	
	Metrics getMetricsBySearch(String name, Double valmin, Double valmax, Integer page);
	Metrics getAllMetricsByPage(Integer page);
	Metrics getMetricByList(String placa, Date min, Date max, Integer page);
	

}
