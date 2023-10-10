package com.example.paq.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.User;
import com.example.paq.entidades.Valoracion;
import com.example.paq.repositorios.ValoracionRepository;
import com.example.paq.repositorios.UserRepository;


@Service
public class ValoracionServiceImpl implements ValoracionService {

	
	@Autowired
    private ValoracionRepository valoracionRepository;
	
	@Autowired
	private UserRepository repoUsuario; 
	
	@Override
	public void registrarValoracion(String user, Album album, int nota) {
		 Valoracion valoracion = new Valoracion();
	        valoracion.setUsuario(findByEmail(user)); // Asignar el usuario autenticado a la valoración
	        valoracion.setAlbum(album);
	        valoracion.setNota(nota);
	        valoracionRepository.save(valoracion);
		
	}
	@Override 
	public User findByEmail(String email) {
		User usuario = repoUsuario.findByEmail(email);
		return usuario; /* TODO document why this method is empty */ }

}
