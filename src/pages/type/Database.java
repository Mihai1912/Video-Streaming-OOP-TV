package pages.type;

import fileinput.Input;
import fileinput.Action;
import fileinput.Movie;
import fileinput.User;
import fileinput.Notification;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Database {
    private static Database instance = null;

    private Database() {
    }

    /**
     * @return instance of Database
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * @param input  dataBase from Json File
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary
     *               fields
     */
    public void database(final Input input, final Action action, final Helper helper) {
        if (action.getFeature().equals("add")) {

            for (Movie movie : input.getMovies()) {
                if (action.getAddedMovie().getName().equals(movie.getName())) {
                    helper.printError();
                    return;
                }
            }

            input.getMovies().add(action.getAddedMovie());

            for (User user : helper.getUsers()) {
                if (!action.getAddedMovie().getCountriesBanned()
                        .contains(user.getCredentials().getCountry())) {
                    user.getMoviesToWatch().add(action.getAddedMovie());
                    for (String genre : action.getAddedMovie().getGenres()) {
                        if (user.getSubscriptions().contains(genre)) {
                            Notification notification = new Notification();
                            notification.setMovieName(action.getAddedMovie().getName());
                            notification.setMessage("ADD");
                            user.getNotifications().add(notification);
                            return;
                        }
                    }
                }
            }

        } else {

            boolean deletedMovieFound = false;

            for (Movie movie : input.getMovies()) {
                if (action.getDeletedMovie().equals(movie.getName())) {
                    input.getMovies().remove(movie);
                    deletedMovieFound = true;
                    break;
                }
            }
            if (!deletedMovieFound) {
                helper.printError();
                return;
            }

            deletedMovieFound = false;

            for (User user : helper.getUsers()) {
                for (Movie movie : user.getMoviesToWatch()) {
                    if (action.getDeletedMovie().equals(movie.getName())) {
                        user.getMoviesToWatch().remove(movie);
                        deletedMovieFound = true;
                        break;
                    }
                }
                if (!deletedMovieFound) {
                    return;
                }

                deletedMovieFound = false;

                for (Movie movie : user.getPurchasedMovies()) {
                    if (action.getDeletedMovie().equals(movie.getName())) {
                        user.getPurchasedMovies().remove(movie);
                        deletedMovieFound = true;
                        break;
                    }
                }
                if (!deletedMovieFound) {
                    return;
                }

                Notification notification = new Notification();
                notification.setMovieName(action.getDeletedMovie());
                notification.setMessage("DELETE");
                user.getNotifications().add(notification);


                for (Movie movie : user.getWatchedMovies()) {
                    if (action.getDeletedMovie().equals(movie.getName())) {
                        user.getWatchedMovies().remove(movie);
                        break;
                    }
                }

                for (Movie movie : user.getRatedMovies()) {
                    if (action.getDeletedMovie().equals(movie.getName())) {
                        user.getRatedMovies().remove(movie);
                        break;
                    }
                }

                for (Movie movie : user.getLikedMovies()) {
                    if (action.getDeletedMovie().equals(movie.getName())) {
                        user.getLikedMovies().remove(movie);
                        break;
                    }
                }

                if (user.getCredentials().getAccountType().equals("premium")) {
                    user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
                }

                if (user.getCredentials().getAccountType().equals("standard")) {
                    user.setTokensCount(user.getTokensCount() + 2);
                }
            }
        }
    }
}
