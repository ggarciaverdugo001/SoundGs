package com.example.paq.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.paq.entidades.Cancion;

public interface CancionRepository extends JpaRepository <Cancion, Long> {
	List<Cancion> findByAlbumId(Long id); 
	List<Cancion> findByNombreContainingIgnoreCase(String nombre);
}
