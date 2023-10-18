package com.example.paq.entidades;

import javax.validation.constraints.NotNull;

public class ResultadosBusqueda {

	
	private Long id; 
	public ResultadosBusqueda(Long id, String tipo, String nombre) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nombre = nombre;
	}
	
	private String tipo; 
	private String nombre;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	
}
