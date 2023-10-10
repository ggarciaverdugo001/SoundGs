package com.example.paq.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@Table(name = "cancion") 

public class Cancion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 100)
	private String nombre; 
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "albumid")
	private Album album;
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 10000)
	private String audio; 

	@NotNull
	@NotBlank
	@ManyToOne(fetch = FetchType.LAZY)
	private Genero genero; 
	
	  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinTable(name = "cancion_artista",
	            joinColumns = @JoinColumn(name = "cancion_id"),
	            inverseJoinColumns = @JoinColumn(name = "artista_id"))
	  @Builder.Default
	  @NotNull
		@NotBlank
	    private List<Artista> artistas = new ArrayList<>();
}
