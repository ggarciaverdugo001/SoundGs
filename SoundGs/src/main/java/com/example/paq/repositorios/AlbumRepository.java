package com.example.paq.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.paq.entidades.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

	List<Album> findByNombreContainingIgnoreCase(String nombre);
	@Query("SELECT a FROM Album a JOIN a.generos g WHERE g.nombre = :nombreGenero")
	   List<Album> findByNombreGenero(@Param("nombreGenero") String nombreGenero);
	
	List<Album> findByArtistasId(Long id); 
	}
