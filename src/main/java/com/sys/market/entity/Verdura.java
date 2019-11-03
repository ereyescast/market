package com.sys.market.entity;

//import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "verdura")
public class Verdura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull(message="no puede ser nulo.")
	@NotBlank(message="no puede estar en blanco.")
	@Column(name = "nombre")
	private String nombre;
	
	@Positive(message="debe ser positivo.")
	@Column(name="precio")
	private double precio;
	
	@NotNull(message="no puede ser nula.")
	@FutureOrPresent(message="tiene fecha inválida.")
	@Column(name = "fecharegistro")
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

	public Verdura(int id, String nombre, double precio, Date fecharegistro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fecharegistro = fecharegistro;
	}

	public Verdura() {

	}
	
	
	
	
}
