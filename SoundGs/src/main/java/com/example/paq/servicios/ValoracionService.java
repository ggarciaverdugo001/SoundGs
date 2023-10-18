package com.example.paq.servicios;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.User;
import com.example.paq.entidades.Valoracion;

public interface ValoracionService {
	 public  void registrarValoracion(String user, Album album, int rating);
	 public User findByEmail(String email);
	public Valoracion buscaPorAlbumUsuario(User usuario,Album album ); 
	public void borraValoracion(Long id); 
}
