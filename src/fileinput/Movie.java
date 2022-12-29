package fileinput;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@JsonIgnoreProperties(value = {"ratings"})
public class Movie {
    private String name;
    private Integer year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    private Integer numLikes = 0;
    private Integer numRatings = 0;
    private Double rating = 0.0;
    private ArrayList<Integer> ratings = new ArrayList<>();

    public Movie(final Movie movie) {
        name = movie.getName();
        year = movie.getYear();
        duration = movie.getDuration();
        genres = new ArrayList<>(movie.getGenres());
        actors = new ArrayList<>(movie.getActors());
        countriesBanned = new ArrayList<>(movie.getCountriesBanned());
        numLikes = movie.getNumLikes();
        numRatings = movie.getNumRatings();
        rating = movie.getRating();
        ratings = movie.getRatings();
    }

    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie{"
                + "name='" + name + '\''
                + ", year=" + year
                + ", duration=" + duration
                + ", genres=" + genres
                + ", actors=" + actors
                + ", countriesBanned=" + countriesBanned
                + ", numLikes=" + numLikes
                + ", numRatings=" + numRatings
                + ", rating=" + rating
                + ", ratings=" + ratings
                + '}';
    }
}
