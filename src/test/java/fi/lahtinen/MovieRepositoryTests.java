package fi.lahtinen;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.lahtinen.domain.MovieRepository;
import fi.lahtinen.domain.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieRepositoryTests {
	@Autowired
	private MovieRepository repository;
	
	@Test
	public void findByTitleShouldReturnMovie() {
		List<Movie> movies = repository.findByTitle("Title");
		assertThat(movies).hasSize(1);
	}
	
	@Test
	public void createNewMovie() {
		Movie movie = new Movie("Jurassic Park", null, "Cloned Dinosaurs in a Theme Park", 1993, true);
		repository.save(movie);
		assertThat(movie.getId()).isNotNull();
	}
}
