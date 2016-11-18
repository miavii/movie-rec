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
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/movielist")
	public String Movielist(Model model) {
		model.addAttribute("movies", repository.findAll());
		return "movielist";
	}

	@RequestMapping(value="/movielist2")
	public String Movielist2(Model model) {
		model.addAttribute("movies", repository.findAll());
		return "movielist";
	}
	
	@RequestMapping(value="/movies", method = RequestMethod.GET)
	public @ResponseBody List<Movie> bookListRest() {
		return (List<Movie>) repository.findAll();
	}
	
	@RequestMapping(value="/movie/{id}", method = RequestMethod.GET)
	public @ResponseBody Movie findMovieRest(@PathVariable("id") Long id){
		return repository.findOne(id);
	}
	
	@RequestMapping(value="/addMovie")
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("genres", gRepository.findAll());
		return "addmovie";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Movie movie) {
		System.out.println("It's sabing");
		repository.save(movie);
		return "redirect:movielist2";
	}
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long movieId, Model model){
		Movie m = repository.findOne(movieId);
		model.addAttribute("movie", m);
		return "edit";
	}

	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long movieId, Model model) {
		repository.delete(movieId);
		return "redirect:../movielist";
	}
}
