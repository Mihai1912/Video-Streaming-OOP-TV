package recommendation;

import fileinput.Movie;
import fileinput.Notification;
import helper.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Recommendation {
    public void giveRecommendation (Helper helper , ArrayList<Movie> movies) {
        ArrayList<String> aux = new ArrayList<>();
        helper.getCurrentUser().getLikeGenres().entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).forEach(o->aux.add(o.getKey()));
        Collections.reverse(aux);

        ArrayList<Movie> unwatchedMovies = helper.getCurrentUser().deepCopyForArray(helper
                .getCurrentUser().getMoviesToWatch());

        for (Movie movie : helper.getCurrentUser().getWatchedMovies()) {
            unwatchedMovies.removeIf(movie1 -> movie1.getName().equals(movie.getName()));
        }

        unwatchedMovies.sort((o1, o2) -> o2.getNumLikes()-o1.getNumLikes());

        for (String a: aux) {
            for (Movie movie: unwatchedMovies) {
                if (movie.getGenres().contains(a)) {
                    Notification notification = new Notification();
                    notification.setMessage("Recommendation");
                    notification.setMovieName(movie.getName());
                    helper.getCurrentUser().getNotifications().add(notification);
                    helper.printRecommendation();
                    return;
                }
            }
        }
        Notification notification = new Notification();
        notification.setMessage("Recommendation");
        notification.setMovieName("No recommendation");
        helper.getCurrentUser().getNotifications().add(notification);
        helper.printRecommendation();
    }
}
