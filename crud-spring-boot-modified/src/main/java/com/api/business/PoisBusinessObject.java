package com.api.business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.api.domain.Pois;
import com.api.dto.PaginationDTO;
import com.api.exception.PoisNotFoundException;
import com.api.repository.PoisRepository;



@Service
public class PoisBusinessObject {
	
	private static final Logger log = LoggerFactory.getLogger(PoisBusinessObject.class);
	
	@Autowired
	private PoisRepository poisRepository;
	
	
	public Pois getPoisById(Long id) {
		 return poisRepository.findById(id).orElseThrow(() -> new PoisNotFoundException(id));
   }
	
	public List<Pois> getPois() {
        return (List<Pois>) poisRepository.findAll();
    }
	
	public Pois getAllPoisByPage(Integer page) {
		Pois retorno = (Pois) poisRepository.getAllPoisByPage(page);
		
		PaginationDTO pagination = this.calculaPaginacao(retorno.getPageNumber(),retorno.getPageTotalLines());
		retorno.setPagination(pagination);
        return retorno;
		
	}
	
	//configurar Pageable
	@SuppressWarnings({ "unchecked", "null" })
	public List<Pois> getPoisByPage(Integer page) {
		Pageable pageable = null;
		pageable.setMaxPageSize(50);
    	pageable.setDefaultPageSize(50);
    	pageable.setPageParameter(String.valueOf(page));
        return (List<Pois>) poisRepository.findAllByPage(pageable);
    }
    
    public Pois getPoisBySearch(String name, String floor,String valmax, Integer page) {
    	Double min = null;
    	Double max = null;
		if(!floor.equals(null)  && !floor.equals("null")) {
			min = Double.valueOf(floor);		
		}
		if(!valmax.equals(null)  && !valmax.equals("null")) {
			max = Double.valueOf(valmax);		
		}
		Pois retorno = (Pois) poisRepository.getPoisBySearch(name, min, max, page);
		
		PaginationDTO pagination = this.calculaPaginacao(retorno.getPageNumber(),retorno.getPageTotalLines());
		retorno.setPagination(pagination);
        return retorno;
    }
    
    
    
    
    
    
    public PaginationDTO calculaPaginacao(Integer pageNumber, Integer totalLines) {
    	PaginationDTO paginationDTO = new PaginationDTO();
    	
    	paginationDTO.setMaxPerPage(50); 
    	paginationDTO.setPageNumber(pageNumber);
    	paginationDTO.setTotalLines(totalLines);
    	paginationDTO.setTotalPages(totalLines/50);
    	
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
    	
    	if((pageNumber +1) <= (totalLines/50)) {
    		paginationDTO.setShowNextOne(true);
    	}else{
    		paginationDTO.setShowNextOne(false);
    	}
    	
    	if((pageNumber +10) < (totalLines/50)) {//+10
    		paginationDTO.setShowNextTen(true);
    	}else{
    		paginationDTO.setShowNextTen(false);
    	}
		return paginationDTO;
    }
    
    
    public void cleanImport() {
    	poisRepository.deleteAll();
    }
	
    public Pois editPois(Pois pois) {
    	return poisRepository.save(pois);
    }
    
    public Pois addPois(Pois pois) {
    	return poisRepository.save(pois);
    }
    
    
    public void deletePois(Long id) {
    	poisRepository.deleteById(id);
    }

	
	
	 public String getTeste() {
		    return "Teste";
	 }
	 
	 
	 
	 
	 public String readFilePois()  {
		    String csvFile = "C:/data/base_pois_def.csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {
	            	// use comma as separator
	                String[] rows = line.split(cvsSplitBy);
	                if(rows[0].equals("nome")) {//se não for a linha de cabecalho
	                	continue;
	                }
	                Pois pois = new Pois();
	                pois.setNome( rows[0]);
	                pois.setRaio( Long.parseLong(rows[1]));
	                pois.setLatitude( Double.valueOf(rows[2]));
	                pois.setLongitude( Double.valueOf(rows[3]));
	                poisRepository.save(pois);
	                log.info("rows [nome= " + rows[0] + " , name=" + rows[1] + " , name=" + rows[2] + " , name=" + rows[3] + "]");
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
			return "Operação realizada!";
	 }
	 
	 public List<Pois> listAllPositions() {
		 return (List<Pois>) poisRepository.findAll();
	 }
	 
	 
	 
	 
}
