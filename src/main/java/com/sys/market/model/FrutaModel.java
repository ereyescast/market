package com.sys.market.model;

import java.time.LocalDate;
//import java.util.Date;

//import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;



public class FrutaModel {
	
	
	private int id;
	
	@NotNull(message="no puede ser nulo.")
	@NotBlank(message="no puede estar en blanco.")
	private String nombre;

	@Positive(message="debe ser positivo.")
	private double precio;
	
	//@NotNull(message="no puede ser nula.")
	//@FutureOrPresent(message="tiene fecha inválida.")
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
	
	public FrutaModel(int id, String nombre, double precio, LocalDate fecharegistro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fecharegistro = fecharegistro;
	}
	
	public FrutaModel() {

	}

	
}
