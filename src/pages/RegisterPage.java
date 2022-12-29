package pages;

import designpattern.visitorpattern.Visitor;
import fileinput.Action;
import fileinput.Input;
import fileinput.Movie;
import fileinput.User;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public final class RegisterPage extends Page {
    private String pageName;

    public RegisterPage(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public boolean actionTaken(final Input input, final Action action, final Helper helper) {
        User user = new User(action.getCredentials());
        input.getUsers().add(user);
        helper.setCurrentUser(user);
        user.setMoviesToWatch(moviesAvailable(input, helper));
        return true;
    }

    /**
     *
     * @param input dataBase from Json File
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return arraylist of movies available for the user depending on the country in which he is
     *         located
     */
    public ArrayList<Movie> moviesAvailable(final Input input, final Helper helper) {
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<Movie> aux = new ArrayList<>(input.getMovies());
        for (Movie movie : aux) {
            if (!movie.getCountriesBanned()
                    .contains(helper.getCurrentUser().getCredentials().getCountry())) {
                if (!movies.contains(movie)) {
                    movies.add(movie);
                }
            }
        }
        return movies;
    }

    @Override
    public String toString() {
        return "RegisterPage{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
