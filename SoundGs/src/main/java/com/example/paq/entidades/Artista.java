package com.example.paq.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "artista") 

public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 100)
	private String nombre;  
	
	@Lob
	@Size(max = 2000)
	private String biografia;
	
	@Size(min = 3, max = 10000)
	private String imagen; 
	
	@NotNull
	@NotBlank
	 @ManyToMany(mappedBy = "artistas") 
	 @Builder.Default
	    private List<Cancion> canciones = new ArrayList<>(); 
	
	@NotNull
	@NotBlank
	 @ManyToMany(mappedBy = "artistas")
	 @Builder.Default
	    private List<Album> albums = new ArrayList<>();
}
