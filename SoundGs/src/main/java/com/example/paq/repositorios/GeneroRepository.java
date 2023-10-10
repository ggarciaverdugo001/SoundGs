package com.example.paq.repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.paq.entidades.Genero; 

public interface GeneroRepository extends JpaRepository <Genero, Long> {
	List<Genero> findByAlbumsId(Long id);
	
}
