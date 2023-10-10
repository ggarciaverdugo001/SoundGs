package com.example.paq.servicios;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.User;

public interface ValoracionService {
	 public  void registrarValoracion(String user, Album album, int rating);
	 public User findByEmail(String email);
	
}
