package com.api.domain;

import java.io.Serializable;
import java.util.HashSet;
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
@DiscriminatorValue(value = "PRODUCT")
@Getter 
@Setter
@NoArgsConstructor
public class Pois implements Serializable {

	private static final long serialVersionUID = 9142959708191247040L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "RAIO")
	private long raio;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Transient
	private Set<Pois> listPois = new HashSet<Pois>();

	@Transient
	private Pageable pageable;
	
	@Transient
	private Integer pageNumber;
	
	@Transient
	private Integer pageTotalLines;
	
	@Transient
	private PaginationDTO pagination;

	
	/*public Pois() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Pois(long id, String nome, long raio, Double latitude, Double longitude, Set<Pois> listPois,
			Pageable pageable, Integer pageNumber, Integer pageTotalLines, PaginationDTO pagination) {
		super();
		this.id = id;
		this.nome = nome;
		this.raio = raio;
		this.latitude = latitude;
		this.longitude = longitude;
		this.listPois = listPois;
		this.pageable = pageable;
		this.pageNumber = pageNumber;
		this.pageTotalLines = pageTotalLines;
		this.pagination = pagination;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public long getRaio() {
		return raio;
	}

	public void setRaio(long raio) {
		this.raio = raio;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Set<Pois> getListPois() {
		return listPois;
	}

	public void setListPois(Set<Pois> listPois) {
		this.listPois = listPois;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageTotalLines() {
		return pageTotalLines;
	}

	public void setPageTotalLines(Integer pageTotalLines) {
		this.pageTotalLines = pageTotalLines;
	}

	public PaginationDTO getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDTO pagination) {
		this.pagination = pagination;
	}*/

	

}
