package com.example.paq.servicios;

import java.util.List;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.User;

public interface Slope1Service {

	
	public void loadData();
	public void calculaMatriz();
	public List<Album> obtenerAlbumesRecomendados(User usuario);
}
