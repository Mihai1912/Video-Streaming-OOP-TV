package helper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileinput.Movie;
import fileinput.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public final class Helper {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Movie> currentMovieList = new ArrayList<>();
    private Movie seeDetailsMovie = new Movie();
    private String currentPage;
    private User currentUser = new User();
    private ArrayNode output;
    private ArrayList<Movie> filteredMovies = new ArrayList<>();
    private Integer seeDetailsAfterFilter = 0;

    /**
     *  print errorStd
     */
    public void printError() {
        output.addObject().putPOJO("error", "Error")
                .putPOJO("currentMoviesList", new ArrayList<>())
                .putPOJO("currentUser", null);
    }

    /**
     *  print correct output
     */
    public void printOutput() {
        output.addObject().putPOJO("error", null)
                .putPOJO("currentMoviesList", deepCopyForArray(currentMovieList))
                .putPOJO("currentUser", new User(currentUser));
    }

    /**
     *
     * @param arrayList desired arraylist
     * @return copy arraylist
     */
    public ArrayList<Movie> deepCopyForArray(final ArrayList<Movie> arrayList) {
        ArrayList<Movie> retArray = new ArrayList<>();
        for (Movie movie : arrayList) {
            retArray.add(new Movie(movie));
        }
        return retArray;
    }

    @Override
    public String toString() {
        return "Helper{"
                + "users=" + users
                + ", currentMovieList=" + currentMovieList
                + ", seeDetailsMovie=" + seeDetailsMovie
                + ", currentPage='" + currentPage + '\''
                + ", currentUser=" + currentUser
                + ", output=" + output
                + '}';
    }
}
