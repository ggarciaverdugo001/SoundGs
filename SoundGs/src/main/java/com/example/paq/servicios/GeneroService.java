package com.example.paq.servicios;

import java.util.List;

import com.example.paq.entidades.Genero;

public interface GeneroService {
	
	public Iterable<Genero> obtenerTodos();

	public Genero obtenerPorId(Long id);

	public void borrar(Long id);

	public Genero insertar(Genero album);
	
	public Genero modificar(Genero album);
	
	List<Genero> buscaPorAlbum(Long id);

}
