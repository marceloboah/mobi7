package com.api.business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Positions;
import com.api.repository.PositionsRepository;

@Service
public class PositionsBusinessObject {
	
private static final Logger log = LoggerFactory.getLogger(PositionsBusinessObject.class);
	
	@Autowired
	private PositionsRepository positionsRepository;
	
	public String readFilePositions()  {
	    String csvFile = "C:/data/posicoes.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] rows = line.split(cvsSplitBy);
                if(rows[0].equals("placa")) {//se não for a linha de cabecalho
                	continue;
                }
                Positions position = new Positions();
                position.setPlaca( rows[0]);
                //position.setDataPosicao( new Date(rows[1]));
                String dateHour = rows[1];
                String[] dataHora= dateHour.split(" GMT-0200");
                
                
                //String original = "Wed Dec 12 2018 13:04:03";
                String original = dataHora[0];
                SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd yyyy HH:mm:ss", Locale.US);
                SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
                Date dt = null;
        		try {
        			dt = sdf1.parse(original);
        			
        			String str = sdf2.format(dt);
        			log.info(str); 
        		} catch (ParseException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		position.setDataPosicao( dt);
                position.setVelocidade( Integer.parseInt(rows[2]));
                position.setLongitude( Double.valueOf(rows[3]));
                position.setLatitude( Double.valueOf(rows[4]));
                position.setIgnicao( Boolean.parseBoolean(rows[5]));
                positionsRepository.save(position);

                log.info("rows [placa= " + rows[0] + " , dataPosicao=" + rows[1] + " , velocidade=" + rows[2] + " , longitude=" + rows[3]+ " , latitude=" + rows[4] + " , ignicao=" + rows[5] + "]");


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

	public void cleanImport() {
    	positionsRepository.deleteAll();
    }
	
	public List<Positions> listAllPositions() {
		 return (List<Positions>) positionsRepository.findAll();
	 }
}
