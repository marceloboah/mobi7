package com.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@DiscriminatorValue(value = "POSITIONS")
@Getter 
@Setter
@NoArgsConstructor
public class Positions implements Serializable {
	
	

	/**
	 * @autor Marcelo Boá
	 * @since 08/11/2020
	 */
	private static final long serialVersionUID = -813787027401588713L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "PLACA")
	private String placa;
	
	@Column(name = "DATA_POSICAO")
	private Date dataPosicao;
	
	@Column(name = "VELOCIDADE")
	private Integer velocidade;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "IGNICAO")
	private Boolean ignicao;

	/*public Positions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Positions(long id, String placa, Date dataPosicao, Integer velocidade, Double latitude, Double longitude,
			Boolean ignicao) {
		super();
		this.id = id;
		this.placa = placa;
		this.dataPosicao = dataPosicao;
		this.velocidade = velocidade;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ignicao = ignicao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getDataPosicao() {
		return dataPosicao;
	}

	public void setDataPosicao(Date dataPosicao) {
		this.dataPosicao = dataPosicao;
	}

	public Integer getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Integer velocidade) {
		this.velocidade = velocidade;
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

	public Boolean getIgnicao() {
		return ignicao;
	}

	public void setIgnicao(Boolean ignicao) {
		this.ignicao = ignicao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	*/
	
	
}
