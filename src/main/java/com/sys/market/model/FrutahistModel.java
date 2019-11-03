package com.sys.market.model;

import java.time.LocalDate;
//import java.util.Date;

public class FrutahistModel {
	private int id;
	private String nombre;
	private double precio;
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
	
	public FrutahistModel(int id, String nombre, double precio, LocalDate fecharegistro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fecharegistro = fecharegistro;
	}
	
	public FrutahistModel() {

	}
}
