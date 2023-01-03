package pages.type;

import fileinput.*;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Database {
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

    public void database(Input input, Action action, Helper helper) {
        if (action.getFeature().equals("add")) {

            for (Movie movie : input.getMovies()) {
                if (action.getAddedMovie().getName().equals(movie.getName())) {
                    helper.printError();
                    return;
                }
            }

            input.getMovies().add(action.getAddedMovie());

            for (User user : helper.getUsers()) {
                if (!action.getAddedMovie().getCountriesBanned().contains(user.getCredentials().getCountry())) {
                    user.getMoviesToWatch().add(action.getAddedMovie());
                    for (String genre : action.getAddedMovie().getGenres()) {
                        if (user.getSubscriptions().contains(genre)) {
                            Notification notification = new Notification();
                            notification.setMovieName(action.getAddedMovie().getName());
                            notification.setMessage("ADD");
                            user.getNotifications().add(notification);
                        }
                    }
                }
            }

        } else {

        }
    }
}
