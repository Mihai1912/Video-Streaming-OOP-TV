package pages;

import designpattern.visitorpattern.Visitor;
import fileinput.Action;
import fileinput.Input;
import fileinput.Movie;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public final class MoviesPage extends Page {
    private String pageName;

    public MoviesPage(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public boolean actionTaken(final Input input, final Action action, final Helper helper) {
        if (action.getType().equals("change page")) {
            helper.setCurrentMovieList(helper.getCurrentUser().getMoviesToWatch());
            return true;
        }
        switch (action.getFeature()) {
            case "search" -> {
                helper.setCurrentMovieList(searchFeature(action, helper));
                return true;
            }
            case "filter" -> {
                helper.setCurrentMovieList(filterFeature(action, helper));
                helper.setSeeDetailsAfterFilter(1);
                helper.setFilteredMovies(filterFeature(action, helper));
                return true;
            }
            default -> { }
        }
        return false;
    }

    /**
     *
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return arraylist that will contain the searched movie
     */
    public ArrayList<Movie> searchFeature(final Action action, final Helper helper) {
        ArrayList<Movie> moviesFound = new ArrayList<>();
        for (Movie movie : helper.getCurrentMovieList()) {
            if (movie.getName().startsWith(action.getStartsWith())) {
                moviesFound.add(movie);
            }
        }
        return moviesFound;
    }

    /**
     *
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return arraylist that will contain movies filtered according to the desired criteria
     */
    public ArrayList<Movie> filterFeature(final Action action, final Helper helper) {
        ArrayList<Movie> filteredMovies = helper.getCurrentUser()
                .deepCopyForArray(helper.getCurrentUser().getMoviesToWatch());
        if (action.getFilters().getContains() != null) {
            if (action.getFilters().getContains().getActors() != null) {
                filteredMovies.removeIf(movie -> !movie.getActors()
                        .containsAll(action.getFilters().getContains().getActors()));
            }
            if (action.getFilters().getContains().getGenre() != null) {
                filteredMovies.removeIf(movie -> !movie.getGenres()
                        .containsAll(action.getFilters().getContains().getGenre()));
            }
        }
        if (action.getFilters().getSort() != null) {
            if (action.getFilters().getSort().getDuration() != null
                    && action.getFilters().getSort().getRating() != null) {
                filteredMovies.sort((o1, o2) -> {
                    if (action.getFilters().getSort().getDuration().equals("increasing")) {
                        if (o1.getDuration() == o2.getDuration()) {
                            return ratingSort(action, o1, o2);
                        } else if (o1.getDuration() > o2.getDuration()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    } else {
                        if (o1.getDuration() == o2.getDuration()) {
                            return ratingSort(action, o1, o2);
                        } else if (o1.getDuration() > o2.getDuration()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                });
            }
            if (action.getFilters().getSort().getDuration() == null
                    && action.getFilters().getSort().getRating() != null) {
                filteredMovies.sort((o1, o2) -> ratingSort(action, o1, o2));
            }
        }
        return filteredMovies;
    }

    private int ratingSort(final Action action, final Movie o1, final Movie o2) {
        if (action.getFilters().getSort().getRating().equals("increasing")) {
            if (o1.getRating() > o2.getRating()) {
                return 1;
            } else if (o1.getRating() < o2.getRating()) {
                return -1;
            }
        } else {
            if (o1.getRating() > o2.getRating()) {
                return -1;
            } else if (o1.getRating() < o2.getRating()) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "MoviesPage{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
