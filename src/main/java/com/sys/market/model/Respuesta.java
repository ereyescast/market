package com.sys.market.model;

import java.util.List;

public class Respuesta {

	private boolean resp;
	
	private String campo;
	
	private String mensaje;
	
	private String categoria;
	
	private List<FrutaModel> frutas;
	
	private List<VerduraModel> verduras;
	
	private List<Respuesta> respuestas;
	
	public Respuesta() {
		super();
	}

	public Respuesta(boolean respuesta, String b, List<VerduraModel> verduras) {
		this.resp = respuesta;
		this.verduras = verduras;
	}
	
	public Respuesta(boolean respuesta, List<FrutaModel> frutas) {
		this.resp = respuesta;
		this.frutas = frutas;
	}
	


	
	public Respuesta(boolean respuesta, String mensaje, String categoria) {
		super();
		this.resp = respuesta;
		this.mensaje = mensaje;
		this.categoria = categoria;
	}
	
	public Respuesta(String campo, String mensaje, String categoria) {
		this.campo = campo;
		this.mensaje = mensaje;
		this.categoria = categoria;
	}
	
	public boolean isResp() {
		return resp;
	}

	public void setResp(boolean resp) {
		this.resp = resp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public List<FrutaModel> getFrutas() {
		return frutas;
	}

	public void setFrutas(List<FrutaModel> frutas) {
		this.frutas = frutas;
	}

	public List<VerduraModel> getVerduras() {
		return verduras;
	}

	public void setVerduras(List<VerduraModel> verduras) {
		this.verduras = verduras;
	}
}
