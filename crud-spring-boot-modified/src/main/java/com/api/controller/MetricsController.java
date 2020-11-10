package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.business.MetricsBusinessObject;
import com.api.domain.Metrics;
import com.api.repository.MetricsRepository;



@RestController
@RequestMapping("api")
@CrossOrigin({"*"})
public class MetricsController {
	
	@Autowired
	private MetricsBusinessObject metricsBusinessObject;
	
	@Autowired
	private MetricsRepository metricsRepository;
	
	@GetMapping("metrics/generate")
    public String getMetricsGenerate() {
		return metricsBusinessObject.getMetricsGenerate();
    }
	
	@GetMapping("metrics/all")
    public List<Metrics> getAllMetrics() {
        return metricsBusinessObject.getMetrics();
    }

	@GetMapping("metrics/allByPage")
    public Metrics getPois(@RequestParam(value="page",required=false)  Integer page) {
        return metricsBusinessObject.getAllMetricsByPage(page);
    }
	
	@GetMapping("metrics/search")
    public Metrics getMetricsBySearch(
    		@RequestParam(value="placa")  String placa,
    		@RequestParam(value="valmin")  String valmin,
    		@RequestParam(value="valmax") String valmax,
    		@RequestParam(value="page") Integer page) {
		Metrics retorno = metricsBusinessObject.getMetricsByList(placa, valmin, valmax, page);
		return retorno;
    }
}
