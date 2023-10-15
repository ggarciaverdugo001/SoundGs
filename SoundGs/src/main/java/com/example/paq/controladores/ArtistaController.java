package com.example.paq.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.Artista;
import com.example.paq.entidades.Genero;
import com.example.paq.servicios.AlbumService;
import com.example.paq.servicios.ArtistaService;
import com.example.paq.servicios.GeneroService;

@Controller
public class ArtistaController {
	@Autowired
	ArtistaService servicio;
	
	@Autowired
	GeneroService generoServicio; 
	
	@Autowired
	AlbumService servicioAlbum;
	
	
	@GetMapping("/Artista/{id}")
	public String mostrarDetalle(@PathVariable Long id, Model modelo) {
		Artista detalle = servicio.obtenerPorId(id);
		modelo.addAttribute("artista", detalle); 
		Iterable<Genero> generos = generoServicio.obtenerTodos();
		modelo.addAttribute("generos", generos);
		Iterable<Album> albumes = servicioAlbum.buscaArtistaId(id);
		modelo.addAttribute("albumes", albumes);
		for(Album a: albumes) {
			System.out.println(a.getPortada());
		}
		
		return "/artista";
	}
}
