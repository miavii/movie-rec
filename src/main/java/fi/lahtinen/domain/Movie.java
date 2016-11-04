package fi.lahtinen.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title, summary, rating, status; //should summary be its own object? is there a better way to do rating and status???
	private int year;
	@ManyToOne
	@JoinColumn(name = "genreId")
	private Genre genre;
	public Movie() {}

	public Movie(String title, Genre genre, String summary, int year, String status) {
		super();
		this.title = title;
		this.genre = genre;
		this.summary = summary;
		this.year = year;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", summary=" + summary
				+ ", rating=" + rating + ", status=" + status + ", year="
				+ year + ", genre=" + genre + "]";
	}

}
