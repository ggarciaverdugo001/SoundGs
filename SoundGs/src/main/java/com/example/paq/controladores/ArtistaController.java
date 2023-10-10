package com.example.paq.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.paq.entidades.Artista;
import com.example.paq.servicios.ArtistaService;

@Controller
@RequestMapping("/artista")
public class ArtistaController {
	@Autowired
	ArtistaService servicio;
	
	
	@GetMapping("/{id}")
	public String mostrarDetalle(@PathVariable Long id, Model modelo) {
		Artista detalle = servicio.obtenerPorId(id);
		modelo.addAttribute("artista", detalle); 
		
		return "/artista";
	}
}
