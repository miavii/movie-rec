package fi.lahtinen;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.lahtinen.domain.Genre;
import fi.lahtinen.domain.Movie;
import fi.lahtinen.domain.GenreRepository;
import fi.lahtinen.domain.MovieRepository;

@SpringBootApplication
public class MoviesApplication {
	@Autowired
	private MovieRepository repository;
	@Autowired
	private GenreRepository gRepository;
	private static final Logger log = LoggerFactory.getLogger(MoviesApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieDemo(){
		return (args) -> {
			log.info("Saving some genres");
			gRepository.save(new Genre("Comedy", "Funny stuff"));
			gRepository.save(new Genre("Action", "Shooty stuff"));
			gRepository.save(new Genre("Romance", "Lovey stuff"));
			gRepository.save(new Genre("Sci-fi", "Scifi stuff"));
			repository.save(new Movie("Title 1", gRepository.findByGenreName("Comedy").get(0), "Summaru", 1233));
			repository.save(new Movie("Title 2", gRepository.findByGenreName("Sci-fi").get(0), "Summaru", 1997));
			repository.save(new Movie("Title 3", gRepository.findByGenreName("Action").get(0), "Summaru", 1980));
			repository.save(new Movie("Title 4", gRepository.findByGenreName("Romance").get(0), "Summaru", 2010));
			
			log.info("fetch all movies");
			for (Movie movie : repository.findAll()) {
				log.info(movie.toString());
			}
		};
	}
}
