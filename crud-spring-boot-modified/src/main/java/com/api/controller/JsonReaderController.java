package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.business.PoisBusinessObject;
import com.api.business.PositionsBusinessObject;

@RestController
@RequestMapping("api")
@CrossOrigin({"*"})
public class JsonReaderController {
	
	@Autowired
	private PoisBusinessObject poisBusinessObject;
	
	@Autowired
	private PositionsBusinessObject positionsBusinessObject;
	
	@GetMapping("import/pois")
    public String initReaderPois() {
		return poisBusinessObject.readFilePois();
    }
	
	@GetMapping("import/positions")
    public String initReaderPositions() {
		return positionsBusinessObject.readFilePositions();
    }
	
	@GetMapping("clean/import/pois")
    public String cleanImportPois() {
		poisBusinessObject.cleanImport();
		return poisBusinessObject.readFilePois();
    }
	
	@GetMapping("clean/import/positions")
    public String cleanImportPositions() {
		positionsBusinessObject.cleanImport();
		return positionsBusinessObject.readFilePositions();
    }

}
