package com.example.paq.servicios;

import java.util.List;

import com.example.paq.entidades.Artista;


public interface ArtistaService {
	
	public Iterable<Artista> obtenerTodos();
 
	public Artista obtenerPorId(Long id);

	public void borrar(Long id);

	public Artista insertar(Artista album);
	
	public Artista modificar(Artista album);
	
	List<Artista> buscaPorArtista(Long id);

	List<Artista> buscaPorNombre(String nombre);
}
