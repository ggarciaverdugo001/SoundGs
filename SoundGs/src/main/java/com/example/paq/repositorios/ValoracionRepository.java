package com.example.paq.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.User;
import com.example.paq.entidades.Valoracion;

public interface ValoracionRepository extends JpaRepository <Valoracion, Long> {

	 Valoracion findByUsuarioAndAlbum(User usuario, Album album);

}
