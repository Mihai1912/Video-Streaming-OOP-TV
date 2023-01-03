package pages;

import designpattern.visitorpattern.Visitor;
import fileinput.Action;
import fileinput.Input;
import fileinput.Movie;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
public final class SeeDetailsPage extends Page {
    private String pageName;

    public SeeDetailsPage(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public boolean actionTaken(final Input input, final Action action, final Helper helper) {
        if (action.getType().equals("change page")) {
            if (helper.getSeeDetailsAfterFilter() == 0) {
                if (helper.getCurrentUser().getMoviesToWatch().isEmpty()) {
                    return false;
                }
                helper.setCurrentMovieList(new ArrayList<Movie>());
                for (Movie movie : helper.getCurrentUser().getMoviesToWatch()) {
                    if (movie.getName().equals(action.getMovie())) {
                        helper.setSeeDetailsMovie(movie);
                        if (helper.getCurrentMovieList().isEmpty()) {
                            helper.getCurrentMovieList().add(movie);
                        }
                        return true;
                    }
                }
                return false;
            } else {
                if (helper.getFilteredMovies().isEmpty()) {
                    return false;
                }
                for (Movie movie : helper.getFilteredMovies()) {
                    if (movie.getName().equals(action.getMovie())) {
                        helper.setSeeDetailsMovie(movie);
                        if (helper.getCurrentMovieList().isEmpty()) {
                            helper.getCurrentMovieList().add(movie);
                        }
                        helper.setSeeDetailsAfterFilter(0);
                        return true;
                    }
                }
                return false;
            }
        }
        switch (action.getFeature()) {
            case "purchase" -> {
                if (purchaseFeature(helper).isEmpty()) {
                    return false;
                }
                helper.getCurrentMovieList().addAll(purchaseFeature(helper));
                return true;
            }
            case "watch" -> {
                if (watchFeature(helper).isEmpty()) {
                    return false;
                }
                helper.setCurrentMovieList(new ArrayList<>());
                helper.getCurrentMovieList().addAll(watchFeature(helper));
                return true;
            }
            case "like" -> {
                if (likeFeature(helper).isEmpty()) {
                    return false;
                }
                helper.getCurrentMovieList().addAll(likeFeature(helper));
                return true;
            }
            case "rate" -> {
                if (rateFeature(action, helper).isEmpty()) {
                    return false;
                }
                helper.setCurrentMovieList(new ArrayList<>());
                helper.getCurrentMovieList().addAll(rateFeature(action, helper));
                return true;
            }
            case "subscribe" -> {
                if (subscribeFeature(helper , action)) {
                    helper.setPrintOut(false);
                    return true;
                }
                return false;
            }
            default -> {
            }
        }
        return false;
    }

    /**
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return arraylist with the purchased movies
     */
    public ArrayList<Movie> purchaseFeature(final Helper helper) {
        ArrayList<Movie> purchasedMovies = new ArrayList<>();
        if (helper.getCurrentUser().getMoviesToWatch().isEmpty()) {
            return purchasedMovies;
        }
        if (helper.getCurrentUser().getMoviesToWatch().contains(helper.getSeeDetailsMovie())) {
            if (helper.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
                if (helper.getCurrentUser().getNumFreePremiumMovies() > 0) {
                    if (helper.getCurrentUser().getPurchasedMovies()
                            .contains(helper.getSeeDetailsMovie())) {
                        return purchasedMovies;
                    }
                    helper.getCurrentUser().getPurchasedMovies().add(helper.getSeeDetailsMovie());
                    helper.getCurrentUser().setNumFreePremiumMovies(helper.getCurrentUser()
                            .getNumFreePremiumMovies() - 1);
                    purchasedMovies.add(helper.getSeeDetailsMovie());
                    return purchasedMovies;
                } else {
                    if (helper.getCurrentUser().getPurchasedMovies()
                            .contains(helper.getSeeDetailsMovie())) {
                        return purchasedMovies;
                    }
                    helper.getCurrentUser().getPurchasedMovies().add(helper.getSeeDetailsMovie());
                    helper.getCurrentUser().setTokensCount(helper.getCurrentUser()
                            .getTokensCount() - 2);
                    purchasedMovies.add(helper.getSeeDetailsMovie());
                    return purchasedMovies;
                }
            } else {
                if (helper.getCurrentUser().getPurchasedMovies()
                        .contains(helper.getSeeDetailsMovie())) {
                    return purchasedMovies;
                }
                helper.getCurrentUser().getPurchasedMovies().add(helper.getSeeDetailsMovie());
                helper.getCurrentUser().setTokensCount(helper.getCurrentUser()
                        .getTokensCount() - 2);
                purchasedMovies.add(helper.getSeeDetailsMovie());
                return purchasedMovies;
            }
        }
        return purchasedMovies;
    }

    /**
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return arraylist with the movies seen
     */
    public ArrayList<Movie> watchFeature(final Helper helper) {
        ArrayList<Movie> watchedMovies = new ArrayList<>();
        if (helper.getCurrentUser().getPurchasedMovies().isEmpty()) {
            return watchedMovies;
        }
        if (helper.getCurrentUser().getPurchasedMovies().contains(helper.getSeeDetailsMovie())) {
            if (helper.getCurrentUser().getWatchedMovies().contains(helper.getSeeDetailsMovie())) {
                watchedMovies.add(helper.getSeeDetailsMovie());
                return watchedMovies;
            }
            helper.getCurrentUser().getWatchedMovies().add(helper.getSeeDetailsMovie());
            watchedMovies.add(helper.getSeeDetailsMovie());
            return watchedMovies;
        }
        return watchedMovies;
    }

    /**
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return arraylist with popular movies
     */
    public ArrayList<Movie> likeFeature(final Helper helper) {
        ArrayList<Movie> likeMovie = new ArrayList<>();
        if (helper.getCurrentUser().getWatchedMovies().isEmpty()) {
            return likeMovie;
        }
        if (helper.getCurrentUser().getWatchedMovies().contains(helper.getSeeDetailsMovie())) {
            if (helper.getCurrentUser().getLikedMovies().contains(helper.getSeeDetailsMovie())) {
                return likeMovie;
            }
            helper.getSeeDetailsMovie().setNumLikes(helper.getSeeDetailsMovie().getNumLikes() + 1);
            helper.getCurrentUser().getLikedMovies().add(helper.getSeeDetailsMovie());
            likeMovie.add(helper.getSeeDetailsMovie());

            for (String genre : helper.getSeeDetailsMovie().getGenres()) {
                if (helper.getCurrentUser().getLikeGenres().containsKey(genre)) {
                    int val = helper.getCurrentUser().getLikeGenres().get(genre);
                    helper.getCurrentUser().getLikeGenres().put(genre , val+1);
                } else {
                    helper.getCurrentUser().getLikeGenres().put(genre, 1);
                }
            }

            return likeMovie;
        }
        return likeMovie;
    }

    /**
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return arraylist with rated movies
     */
    public ArrayList<Movie> rateFeature(final Action action, final Helper helper) {
        ArrayList<Movie> rateMovies = new ArrayList<>();
        if (helper.getCurrentUser().getWatchedMovies().isEmpty()) {
            return rateMovies;
        }
        if (helper.getCurrentUser().getWatchedMovies().contains(helper.getSeeDetailsMovie())) {
            if (action.getRate() > 5) {
                return rateMovies;
            }
            if (helper.getCurrentUser().getRatedMovies().contains(helper.getSeeDetailsMovie())) {
                String name = helper.getCurrentUser().getCredentials().getName();
                helper.getSeeDetailsMovie().getRatings().remove(name);
                helper.getSeeDetailsMovie().getRatings().put(name, (double) action.getRate());
                helper.getSeeDetailsMovie().setRating(calcRating(helper));
                rateMovies.add(helper.getSeeDetailsMovie());
            }
            if (!helper.getCurrentUser().getRatedMovies().contains(helper.getSeeDetailsMovie())) {
                String name = helper.getCurrentUser().getCredentials().getName();
                helper.getSeeDetailsMovie().getRatings().put(name, (double) action.getRate());
                helper.getSeeDetailsMovie()
                        .setNumRatings(helper.getSeeDetailsMovie().getNumRatings() + 1);
                helper.getSeeDetailsMovie().setRating(calcRating(helper));
                helper.getCurrentUser().getRatedMovies().add(helper.getSeeDetailsMovie());
                rateMovies.add(helper.getSeeDetailsMovie());
            }
            return rateMovies;
        }
        return rateMovies;
    }

    public double calcRating(Helper helper) {
        double sum = 0.0;
        for (Double i : helper.getSeeDetailsMovie().getRatings().values()) {
            sum += i;
        }
        return sum / helper.getSeeDetailsMovie().getNumRatings();
    }

    public boolean subscribeFeature (Helper helper , Action action) {
        for (String genre : helper.getSeeDetailsMovie().getGenres()) {
            if (genre.equals(action.getSubscribedGenre())) {
                if (!helper.getCurrentUser().getSubscriptions().contains(genre)) {
                    helper.getCurrentUser().getSubscriptions().add(genre);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * @return page as a string
     */
    @Override
    public String toString() {
        return "SeeDetailsPage{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
