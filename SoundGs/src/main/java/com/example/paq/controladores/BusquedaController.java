package com.example.paq.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.Artista;
import com.example.paq.entidades.Cancion;
import com.example.paq.entidades.ResultadosBusqueda;
import com.example.paq.servicios.AlbumService;
import com.example.paq.servicios.ArtistaService;
import com.example.paq.servicios.CancionService;
@Controller
public class BusquedaController {

	
	@Autowired
	AlbumService servicio;
	
	
	@Autowired
	ArtistaService artistaServicio; 
	
	@Autowired
	CancionService cancionServicio;
	
	  @GetMapping("/buscar")
	  public String buscar(@RequestParam("busqueda") String busqueda, Model model) {
	    List<Album> albums = servicio.buscaPorNombre(busqueda);
	    List<Artista> artistas = artistaServicio.buscaPorNombre(busqueda);
	    List<Cancion> canciones = cancionServicio.buscaPorNombre(busqueda);

	    List<ResultadosBusqueda> resultados = new ArrayList<>();
	    if(artistas != null) {
	    	 for(Artista a: artistas) {
	 	    	ResultadosBusqueda r = new ResultadosBusqueda(a.getId(), "Artista", a.getNombre()); 
	 	    	resultados.add(r); 
	 	    }	
	    }
	   
	    if(albums != null) {
	    for(Album a: albums) {
	    	ResultadosBusqueda r = new ResultadosBusqueda(a.getId(), "Album", a.getNombre()); 
	    	resultados.add(r); 
	    }
	    }
	    

	    model.addAttribute("resultados", resultados);

	    return "buscar";
	  }
}
