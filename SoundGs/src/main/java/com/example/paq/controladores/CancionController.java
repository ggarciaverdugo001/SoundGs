package com.example.paq.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.Cancion;
import com.example.paq.servicios.CancionService;



@Controller
@RequestMapping("/canciones")
public class CancionController {
	
	@Autowired
	CancionService servicio;
	
	@GetMapping("/")
	public String MostrarListado(Model modelo) {
		Iterable<Cancion> canciones = servicio.obtenerTodos(); 
		modelo.addAttribute("canciones", canciones); 
		return ("/canciones");
		
	}
	
	

}
