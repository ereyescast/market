package com.sys.market.model;

//import java.time.LocalDate;
import java.util.Date;

public class VerdurahistModel {

	private int id;
	private String nombre;
	private double precio;
	private Date fecharegistro;
	
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
	public Date getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	
	public VerdurahistModel(int id, String nombre, double precio, Date fecharegistro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fecharegistro = fecharegistro;
	}
	
	public VerdurahistModel() {

	}
}
