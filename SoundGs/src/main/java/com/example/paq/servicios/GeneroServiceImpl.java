package com.example.paq.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paq.entidades.Genero;
import com.example.paq.repositorios.GeneroRepository;

@Service
public class GeneroServiceImpl implements GeneroService {

	@Autowired
	GeneroRepository repo;

	@Override
	public Iterable<Genero> obtenerTodos() {
		return repo.findAll();
	}

	@Override
	public Genero obtenerPorId(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Genero insertar(Genero album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genero modificar(Genero album) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genero> buscaPorAlbum(Long id) {
		return repo.findByAlbumsId(id);
	}

}
