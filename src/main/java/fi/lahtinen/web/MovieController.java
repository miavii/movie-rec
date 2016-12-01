package fi.lahtinen.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.lahtinen.domain.GenreRepository;
import fi.lahtinen.domain.Movie;
import fi.lahtinen.domain.MovieRepository;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository gRepository;
	
	// Login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// Movielist for Registered User
	@RequestMapping(value="/movielist")
	public String Movielist(Model model) {
		model.addAttribute("movies", repository.findAll());
		return "movielist";
	}
	
	// Movielist for anonymous
	@RequestMapping(value="/movielist2")
	public String Movielist2(Model model) {
		model.addAttribute("movies", repository.findAll());
		return "movielist";
	}
	
	// Fetch all movies
	@RequestMapping(value="/movies", method = RequestMethod.GET)
	public @ResponseBody List<Movie> bookListRest() {
		return (List<Movie>) repository.findAll();
	}
	
	// Find A Movie by it's id
	@RequestMapping(value="/movie/{id}", method = RequestMethod.GET)
	public @ResponseBody Movie findMovieRest(@PathVariable("id") Long id){
		return repository.findOne(id);
	}
	
	// Create a new movie object and fetch genres for dropdown option
	// Add movie html
	@RequestMapping(value="/addMovie")
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("genres", gRepository.findAll());
		return "addmovie";
	}
	
	// Save the added movie to the repository or save edited movie, redirect to list.
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Movie movie) {
		repository.save(movie);
		return "redirect:movielist2";
	}
	
	// Edit movie by id, move to edit html.
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long movieId, Model model){
		Movie m = repository.findOne(movieId);
		model.addAttribute("movie", m);
		return "edit";
	}
	
	// Delete Movie by Id, redirect back to the list
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long movieId, Model model) {
		repository.delete(movieId);
		return "redirect:../movielist";
	}
}
