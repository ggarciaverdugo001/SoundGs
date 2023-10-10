package com.example.paq.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paq.entidades.Album;
import com.example.paq.repositorios.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService{
	
	@Autowired
	AlbumRepository repo; 

	@Override
	public Iterable<Album> obtenerTodos() {
		return repo.findAll();
	}

	@Override
	public Album obtenerPorId(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void borrar(Long id) {
		repo.deleteById(id);		
	}

	@Override
	public Album insertar(Album album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Album modificar(Album album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> buscaPorNombre(String nombre) {
		  return repo.findByNombreContainingIgnoreCase(nombre);
	}

	@Override
	public List<Album>buscaPorGenero(String genero) {
	    return repo.findByNombreGenero(genero);
	}

	
}
