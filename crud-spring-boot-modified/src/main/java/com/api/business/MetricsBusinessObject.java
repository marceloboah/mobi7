package com.api.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Metrics;
import com.api.domain.Pois;
import com.api.domain.Positions;
import com.api.dto.PaginationDTO;
import com.api.repository.MetricsRepository;

@Service
public class MetricsBusinessObject {
	
private static final Logger log = LoggerFactory.getLogger(MetricsBusinessObject.class);
	
	@Autowired
	private MetricsRepository metricsRepository;
	
	@Autowired
	private PoisBusinessObject poisBusinessObject;
	
	@Autowired
	private PositionsBusinessObject positionsBusinessObject;

	public String getMetricsGenerate() {
		
		SimpleDateFormat dataHr = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");

		List<Positions> positions = positionsBusinessObject.listAllPositions();
		
		List<Pois> pois = poisBusinessObject.listAllPositions();
		
		for (Iterator<Positions> iterator2 = positions.iterator(); iterator2.hasNext();) {
			Positions position = (Positions) iterator2.next();
			for (Iterator<Pois> iterator = pois.iterator(); iterator.hasNext();) {
				Pois poi = (Pois) iterator.next();
			
				double dist = distance(poi.getLatitude(),position.getLatitude(),poi.getLongitude(),position.getLongitude(),0,0);
				if(dist<=poi.getRaio()) {
					Metrics metrics = new Metrics();
					String dtH = dataHr.format(position.getDataPosicao());
					
					metrics.setRaioReal((int)dist);
					metrics.setDataPosicao(position.getDataPosicao());
					metrics.setDtHoraPosicao(dtH);
					metrics.setIgnicao(position.getIgnicao());
					metrics.setLatitude(position.getLatitude());
					metrics.setLongitude(position.getLongitude());
					metrics.setNomePonto(poi.getNome());
					metrics.setPlaca(position.getPlaca());
					metrics.setRaioLimite(poi.getRaio());
					metrics.setVelocidade(position.getVelocidade());
					metricsRepository.save(metrics);
					log.info(position.getPlaca()+" - "+position.getId()+" - "+poi.getNome());
					//log.info("Latitude="+position.getLatitude().toString());
					//log.info("Longitude="+position.getLongitude().toString());
					log.info(position.getVelocidade().toString());
					//log.info("Latitude="+poi.getLatitude().toString());
					//log.info("Longitude="+poi.getLongitude().toString());

					log.info(String.valueOf(dist));
				}
			}
			
			
		}
		
		return "Calculado!";
	}
	
	public static double distance(double lat1, double lat2, double lon1,double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
	
	
	public PaginationDTO calculaPaginacao(Integer pageNumber, Integer totalLines) {
    	PaginationDTO paginationDTO = new PaginationDTO();
    	
    	paginationDTO.setMaxPerPage(50); 
    	paginationDTO.setPageNumber(pageNumber);
    	paginationDTO.setTotalLines(totalLines);
    	
    	int totalPage = totalLines/50;
    	if(totalLines % 50 > 0) {
    		totalPage++;
    	}
    	paginationDTO.setTotalPages(totalPage);
    	
    	if((pageNumber -10) > 0) { //-10
    		paginationDTO.setShowBackTen(true);
    	}else{
    		paginationDTO.setShowBackTen(false);
    	}
    	
    	if((pageNumber -1) >= 1) {
    		paginationDTO.setShowBackOne(true);
    	}else{
    		paginationDTO.setShowBackOne(false);
    	}
    	
    	if((pageNumber +1) <= (totalPage)) {
    		paginationDTO.setShowNextOne(true);
    	}else{
    		paginationDTO.setShowNextOne(false);
    	}
    	
    	if((pageNumber +10) < (totalPage)) {//+10
    		paginationDTO.setShowNextTen(true);
    	}else{
    		paginationDTO.setShowNextTen(false);
    	}
		return paginationDTO;
    }

	public List<Metrics> getMetrics() {
		return (List<Metrics>) metricsRepository.findAll();
	}
	
	public Metrics getAllMetricsByPage(Integer page) {
		Metrics retorno = (Metrics) metricsRepository.getAllMetricsByPage(page);
		
		PaginationDTO pagination = this.calculaPaginacao(retorno.getPageNumber(),retorno.getPageTotalLines());
		retorno.setPagination(pagination);
        return retorno;
		
	}

	public Metrics getMetricsByList(String placa, String valmin, String valmax, Integer page) {
			Date min = null;
			Date max = null;
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			
			if(valmin != null && !valmin.equals("null")) {
				try {
					
					min = formato.parse(valmin);
					
				} catch (Exception e) {
					e.getMessage(); 
				}
			}
			if(valmax != null && !valmin.equals("null")) {
				try {
					max = formato.parse(valmax);
					
				} catch (Exception e) {
					e.getMessage(); 
				}
			}
			
			Metrics retorno = (Metrics) metricsRepository.getMetricByList(placa, min, max, page);
			
			PaginationDTO pagination = this.calculaPaginacao(retorno.getPageNumber(),retorno.getPageTotalLines());
			retorno.setPagination(pagination);
			
	        return retorno;
	    }

}
