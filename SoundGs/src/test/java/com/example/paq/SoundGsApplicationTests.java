package com.example.paq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.paq.repositorios.AlbumRepository;

@SpringBootTest
class SoundGsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	AlbumRepository repo; 
	 
	
	@Test

	public void pruebarepository(){
		System.out.println(repo.findAll()); 
	}
	
	
	@Test

	public void pruebarepository2(){
		System.out.println(repo.findByNombreContainingIgnoreCase("Acid")); 
	}

}
