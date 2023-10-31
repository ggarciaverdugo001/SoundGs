package com.example.paq.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.User;
import com.example.paq.repositorios.UserRepository;
import com.example.paq.servicios.Slope1;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    @Autowired
    private Slope1 slope1Service; // Inyecta el servicio que calcula las recomendaciones
    
    
    @Autowired
	private UserRepository userRepo;
    
    @GetMapping("/usuario/{usuarioId}")
    public String mostrarRecomendaciones(@PathVariable Long usuarioId, Model model) {
        // Obtiene el usuario a partir del usuarioId (puedes hacer esto como sea necesario)
       User usuario = userRepo.getById(usuarioId);
       slope1Service.loadData();

        // Asegúrate de haber calculado las recomendaciones previamente
        slope1Service.calculaMatriz();

        // Obtiene las recomendaciones para el usuario
        List<Album> recomendaciones = slope1Service.obtenerAlbumesRecomendados(usuario);

        // Agrega las recomendaciones al modelo para que se muestren en la vista
        model.addAttribute("recomendaciones", recomendaciones);

        // Retorna el nombre de la vista donde se mostrarán las recomendaciones
        return "vistaRecomendaciones";
    }
}
