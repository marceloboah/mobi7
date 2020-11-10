package com.api.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.api.domain.Metrics;

import lombok.Data;


@Data
public class MetricsDTO {

	private String placa;
	private Date dataPosicao;
	private Timestamp horaPosicao;
	private Timestamp ultimaLeitura;
	private Integer velocidade;
	private Boolean ignicao;
	private String nomePonto;
	private long raioLimite;
	private Integer raioReal;
	private Double latitude;
	private Double longitude;
	
	private Set<Metrics> listMetrics = new HashSet<Metrics>();
	private Pageable pageable;
	private Integer pageNumber;
	private Integer pageTotalLines;
	private PaginationDTO pagination;
	

}
