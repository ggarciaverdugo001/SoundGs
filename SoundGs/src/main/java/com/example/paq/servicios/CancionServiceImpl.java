
package com.example.paq.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paq.entidades.Cancion;
import com.example.paq.repositorios.CancionRepository;


@Service
public class CancionServiceImpl implements CancionService{

	@Autowired
	CancionRepository repo; 
	
	
	@Override
	public Iterable<Cancion> obtenerTodos() {
		return repo.findAll();
	}

	@Override
	public Cancion obtenerPorId(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void borrar(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Cancion insertar(Cancion album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cancion modificar(Cancion album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Cancion> obtenerTitulos(Long id) {
		return repo.findByAlbumId(id);
	}

	@Override
	public List<Cancion> buscaPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
