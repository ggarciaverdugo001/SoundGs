package com.example.paq.servicios;

import java.util.List;

import com.example.paq.entidades.Cancion;

public interface CancionService {

	
	public Iterable<Cancion> obtenerTodos();

	public Cancion obtenerPorId(Long id);

	public void borrar(Long id);

	public Cancion insertar(Cancion album);
	
	public Cancion modificar(Cancion album);
	
	public Iterable<Cancion> obtenerTitulos(Long id); 
	List<Cancion> buscaPorNombre(String nombre);
	
	
	
	
}
