package com.api.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.domain.Pageable;

import com.api.dto.PaginationDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "METRICS")
@Getter 
@Setter
@NoArgsConstructor
public class Metrics {
	
	@Id
	@GeneratedValue
	private long id;
	
	
	@Column(name = "PLACA")
	private String placa;
	
	@Column(name = "DATA_POSICAO")
	private Date dataPosicao;
	
	@Column(name = "DATA_HORA_POSICAO")
	private String dtHoraPosicao;
	
	@Column(name = "VELOCIDADE")
	private Integer velocidade;
	
	@Column(name = "IGNICAO")
	private Boolean ignicao;
	
	@Column(name = "NOME_PONTO")
	private String nomePonto;
	
	@Column(name = "RAIO_LIMITE")
	private long raioLimite;
	
	@Column(name = "RAIO_REAL")
	private Integer raioReal;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Transient
	private List<Metrics> listMetrics = new ArrayList<Metrics>();

	@Transient
	private Pageable pageable;
	
	@Transient
	private Integer pageNumber;
	
	@Transient
	private Integer pageTotalLines;
	
	@Transient
	private PaginationDTO pagination;

}
