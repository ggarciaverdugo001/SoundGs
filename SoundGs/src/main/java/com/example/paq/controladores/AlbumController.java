package com.example.paq.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.Cancion;
import com.example.paq.entidades.Genero;
import com.example.paq.entidades.User;
import com.example.paq.servicios.AlbumService;
import com.example.paq.servicios.ArtistaService;
import com.example.paq.servicios.CancionService;
import com.example.paq.servicios.CustomUserDetailsService;
import com.example.paq.servicios.GeneroService;
import com.example.paq.servicios.ValoracionService;



@Controller

@RequestMapping("/")
public class AlbumController {
	
	@Autowired
	AlbumService servicio;
	
	@Autowired
	GeneroService generoServicio; 
	
	@Autowired
	ArtistaService artistaServicio; 
	
	@Autowired
	CancionService cancionServicio; 
	
	@Autowired
	ValoracionService valoracionServicio;
	
	@Autowired
	CustomUserDetailsService usuarioServicio; 
	
	
	
	@GetMapping("/")
	public String MostrarListado(Model modelo) {
			Iterable<Album> albums = servicio.obtenerTodos(); 
			modelo.addAttribute("albums", albums); 
	
		Iterable<Genero> generos = generoServicio.obtenerTodos();
		modelo.addAttribute("generos", generos);
		return ("/principal"); 
		
	}
	
	@GetMapping("Album/{id}")
	public String mostrarDetalle(@PathVariable Long id, Model modelo) {
		Album detalle = servicio.obtenerPorId(id);
		detalle.setGeneros(new ArrayList<>(generoServicio.buscaPorAlbum(id)));
		detalle.setArtistas(new ArrayList<>(artistaServicio.buscaPorArtista(id)));
		modelo.addAttribute("detalle", detalle); 
		
		Iterable<Cancion> canciones = cancionServicio.obtenerTitulos(id);
		modelo.addAttribute("canciones", canciones);
		return "/album";
	}
	
	@GetMapping("/filtrar")
	public String filtrarPorGenero(@RequestParam("genero") String genero, Model model) {
	    List<Album> albumsFiltrados = servicio.buscaPorGenero(genero);
	    model.addAttribute("albums", albumsFiltrados);
	    Iterable<Genero> generos = generoServicio.obtenerTodos();
		model.addAttribute("generos", generos);
	    if(genero.equals("Todos")) {
	    	MostrarListado(model);
	    }
	    return "/principal";  
	}
	
	 
	@GetMapping("/valorar/{Id}/{nota}")
	public String miRutaSegura(Authentication authentication, @PathVariable("Id") Long id, @PathVariable int nota, Model modelo) {
	    if (authentication != null && authentication.isAuthenticated()) {
	        Album album = servicio.obtenerPorId(id);
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String username = userDetails.getUsername();
	        
	        // Realiza acciones basadas en la autenticación del usuario
	        valoracionServicio.registrarValoracion(username, album, nota);
	        return "redirect:/"; // Redirige a la página principal
	    } else {
	        return "redirect:/login"; // Redirige al inicio de sesión si el usuario no está autenticado
	    }
	}
	
	
	    @GetMapping("/logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null) {
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	        }
	        return "redirect:/"; // Redirige a la página de inicio de sesión con un mensaje de "logout"
	    }
	

	

	
}
	
	
	



