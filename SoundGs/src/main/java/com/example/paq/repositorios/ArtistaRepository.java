package com.example.paq.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.paq.entidades.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
	List<Artista> findByAlbumsId(Long id);
	List<Artista> findByNombreContainingIgnoreCase(String nombre);

}
