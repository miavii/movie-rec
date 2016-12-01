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
			gRepository.save(new Genre("Crime", "Crimey Stuff"));
			log.info("Saving some Movies");
			repository.save(new Movie("The Godfather", gRepository.findByGenreName("Crime").get(0), "According to Italian tradition, 'no Sicilian can refuse a request on his daughter's wedding day.'", 1972, true));
			repository.save(new Movie("Sausage Party", gRepository.findByGenreName("Comedy").get(0), "A sausage strives to discover the truth about his existence. ", 2016, true));
			repository.save(new Movie("The Terminator", gRepository.findByGenreName("Action").get(0), "A seemingly indestructible humanoid cyborg is sent from 2029.", 1984, true));
			repository.save(new Movie("Titanic", gRepository.findByGenreName("Romance").get(0), "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic. ", 1997, true));
			
			log.info("Fetch all movies");
			for (Movie movie : repository.findAll()) {
				log.info(movie.toString());
			}
		};
	}
}
