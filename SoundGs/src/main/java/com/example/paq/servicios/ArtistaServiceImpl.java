package com.example.paq.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paq.entidades.Artista;
import com.example.paq.repositorios.ArtistaRepository;

@Service
public class ArtistaServiceImpl implements ArtistaService {

	
	@Autowired
	private ArtistaRepository repo; 
	
	
	@Override
	public Iterable<Artista> obtenerTodos() {
		return repo.findAll();
	}

	@Override
	public Artista obtenerPorId(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Artista insertar(Artista album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artista modificar(Artista album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artista> buscaPorArtista(Long id) {
		return repo.findByAlbumsId(id);
	}

	@Override
	public List<Artista> buscaPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
