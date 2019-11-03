package com.sys.market.entity;

import java.time.LocalDate;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "frutaHist")
public class FrutaHist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name="precio")
	private double precio;
	
	@Column(name = "fecharegistro")
	private LocalDate fecharegistro;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public LocalDate getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(LocalDate fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public FrutaHist(int id, String nombre, double precio, LocalDate fecharegistro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fecharegistro = fecharegistro;
	}

	public FrutaHist() {

	}
	
	
	
}
