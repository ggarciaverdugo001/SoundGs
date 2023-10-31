package com.example.paq.servicios;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.User;
import com.example.paq.entidades.Valoracion;
import com.example.paq.repositorios.AlbumRepository;
import com.example.paq.repositorios.UserRepository;
import com.example.paq.repositorios.ValoracionRepository;
import java.util.List;

@Service
public class Slope1 implements Slope1Service {

	@Autowired
	private ValoracionRepository valoracionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AlbumRepository albumRepositorio;

	Map<User, HashMap<Album, Integer>> data = new HashMap<>();

	// Obtener la lista de usuarios, álbumes y valoraciones desde tu base de datos
	

	@Override
	public void loadData() {
		List<Album> albums = albumRepositorio.findAll();
		List<Valoracion> valoraciones = valoracionRepository.findAll();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			HashMap<Album, Integer> userRatings = new HashMap<>();
			for (Album album : albums) {
				// Buscar la valoración correspondiente (si existe)
				Valoracion valoracion = valoracionRepository.findByUsuarioAndAlbum(user, album);
				if (valoracion != null) {
					userRatings.put(album, valoracion.getNota());
				} else {
					// Puedes manejar el caso en el que no haya una valoración
					// aquí, por ejemplo, estableciendo un valor predeterminado.
					userRatings.put(album, 0);
				}
			}
			data.put(user, userRatings);
		}

	}

	
	Map<User, HashMap<Album, Double>> predictions = new HashMap<>();
	Map<Album, HashMap<Album, Double>> diff = new HashMap<>();
	Map<Album, HashMap<Album, Integer>> freq = new HashMap<>();
	@Override
	public void calculaMatriz() {

		List<Album> albums = albumRepositorio.findAll();
		// Iterar a través de las valoraciones de usuarios
		for (Entry<User, HashMap<Album, Integer>> userEntry : data.entrySet()) {
			User user = userEntry.getKey();
			HashMap<Album, Integer> userRatings = userEntry.getValue();
			HashMap<Album, Double> userPredictions = new HashMap<>();

			for (Entry<Album, Integer> ratingEntry : userRatings.entrySet()) {
				Album j = ratingEntry.getKey();
				int rating = ratingEntry.getValue();

				// Inicializar las estructuras de datos si es necesario
				if (!diff.containsKey(j)) {
					diff.put(j, new HashMap<>());
					freq.put(j, new HashMap<>());
				}
		

				// Iterar a través de otros álbumes
				for (Album k : albums) {
					if (k.equals(j)) {
						continue;
					}

					// Calcular la diferencia observada entre los álbumes j y k
					int oldCount = freq.get(j).getOrDefault(k, 0);
					double oldDiff = diff.get(j).getOrDefault(k, 0.0);
					double observedDiff = rating - userRatings.getOrDefault(k, 0);

					// Actualizar las estructuras de datos
					freq.get(j).put(k, oldCount + 1);
					diff.get(j).put(k, oldDiff + observedDiff);
				}
			}

			// Calcular las predicciones para el usuario
			for (Album i : albums) {
				if (!userRatings.containsKey(i)) {
					double userPrediction = 0.0;
					for (Album j : userRatings.keySet()) {
						if (!j.equals(i)) {
							userPrediction += (diff.get(j).get(i) + userRatings.get(j)) * freq.get(j).get(i);
						}
					}
					userPredictions.put(i, userPrediction);
				}
			}

			predictions.put(user, userPredictions);
		}

		// Calcular las predicciones "limpias" y asignar valor predeterminado si es
		// necesario
		Map<User, HashMap<Album, Double>> cleanPredictions = new HashMap<>();
		double defaultValue = -1.0;

		for (User user : data.keySet()) {
			HashMap<Album, Double> userPredictions = predictions.get(user);
			HashMap<Album, Double> cleanUserPredictions = new HashMap<>();

			for (Album album : albums) {
				double prediction = userPredictions.getOrDefault(album, defaultValue);
				cleanUserPredictions.put(album, prediction);
			}

		}

	}
	@Override
	public List<Album> obtenerAlbumesRecomendados(User usuario) {
	    // Calcular las predicciones para el usuario (asegúrate de haber llamado a calculaMatriz() previamente)
	    // userPredictions es un mapa de álbumes a sus predicciones para el usuario
		Map<Album, Double> userPredictions = predictions.get(usuario);


	   
	    // Ordenar las predicciones de mayor a menor
	    List<Album> recomendacionesOrdenadas = userPredictions.entrySet().stream()
	            .sorted(Map.Entry.<Album, Double>comparingByValue().reversed())
	            .map(Map.Entry::getKey)
	            .collect(Collectors.toList());

	    // Limitar la cantidad de recomendaciones a mostrar (por ejemplo, los 10 mejores)
	    int cantidadMaximaRecomendaciones = 5;
	    if (recomendacionesOrdenadas.size() > cantidadMaximaRecomendaciones) {
	        recomendacionesOrdenadas = recomendacionesOrdenadas.subList(0, cantidadMaximaRecomendaciones);
	    }

	    // Ajustar las predicciones al rango de 1 a 5
	    for (Album album : recomendacionesOrdenadas) {
	        userPredictions.put(album, Math.min(5.0, Math.max(1.0, userPredictions.get(album))));
	    }

	    return recomendacionesOrdenadas;
	}
	
	@Autowired
	public Slope1(ValoracionRepository valoracionRepository, UserRepository userRepository, AlbumRepository albumRepository) {
	    this.valoracionRepository = valoracionRepository;
	    this.userRepository = userRepository;
	    this.albumRepositorio = albumRepository;
	}



}
