package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.business.PoisBusinessObject;
import com.api.domain.Pois;
import com.api.repository.PoisRepository;


@RestController
@RequestMapping("api")
@CrossOrigin({"*"})
public class PoisController {
	@Autowired
	private PoisBusinessObject poisBusinessObject;
	
	@Autowired
	private PoisRepository poisRepository;
	
	@GetMapping("pois/{id}")
    public Pois getPoisById(@PathVariable("id") Long id) {
        return poisBusinessObject.getPoisById(id);
    }
	
	@GetMapping("poiss/find")
    public Pois getPoissByFind(
    		@RequestParam(value="name",required=false)  String name,
    	   	@RequestParam(value="floor",required=false)  String floor,
    	   	@RequestParam(value="valmax",required=false) String valmax,
    	   	@RequestParam(value="page",required=false) Integer page) {
		Pois retorno = poisBusinessObject.getPoisBySearch(name, floor, valmax, page);
		return retorno;
    }
	
	
	
	@GetMapping("poises")
    public Pois getPois(@RequestParam(value="page",required=false)  Integer page) {
        return poisBusinessObject.getAllPoisByPage(page);
    }
	
	@GetMapping("pois/all")
    public List<Pois> getAllPois() {
        return poisBusinessObject.getPois();
    }
	
	@PutMapping("pois")
    public Pois editPois(@RequestBody Pois pois) {
		return poisBusinessObject.editPois(pois);
    }
	
	@PostMapping("pois")
    public Pois addPois(@RequestBody Pois pois) {
		return poisBusinessObject.addPois(pois);
    }
    
    @DeleteMapping("pois/{id}")
    public void deletePois(@PathVariable("id") Long number) {
    	poisBusinessObject.deletePois(number);
    }
    
    @GetMapping("pois/populate")
    public List<Pois> getPopulatePois() {
    	return poisBusinessObject.getPois();
    }
    
    @GetMapping("teste")
    public String getTeste() {
        return poisBusinessObject.getTeste();
    }
	


}
