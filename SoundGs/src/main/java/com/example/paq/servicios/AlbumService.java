package com.example.paq.servicios;

import java.util.List;

import com.example.paq.entidades.Album;

public interface AlbumService {

	
	public Iterable<Album> obtenerTodos();

	public Album obtenerPorId(Long id);

	public void borrar(Long id);

	public Album insertar(Album album);
	
	public Album modificar(Album album);
	
	List<Album> buscaPorNombre(String nombre);
	
	List<Album>buscaPorGenero(String genero);
	
}
