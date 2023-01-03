package fileinput;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@JsonIgnoreProperties(value = {"moviesToWatch", "seeDetails", "subscriptions", "likeGenres"})
@Setter
@Getter
public final class User {
    private Credentials credentials;
    private Integer tokensCount;
    private Integer numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;
    private ArrayList<Movie> moviesToWatch;
    private ArrayList<Notification> notifications;
    private ArrayList<String> subscriptions;

    private HashMap<String, Integer> likeGenres = new HashMap<>();

    public User() {
    }

    public User(final Credentials credentials, final Integer tokensCount,
                final Integer numFreePremiumMovies, final ArrayList<Movie> purchasedMovies,
                final ArrayList<Movie> watchedMovies, final ArrayList<Movie> likedMovies,
                final ArrayList<Movie> ratedMovies, final ArrayList<Movie> moviesToWatch,
                final ArrayList<Notification> notifications, final ArrayList<String> subscriptions,
                final HashMap<String, Integer> likeGenres) {
        this.credentials = credentials;
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
        this.moviesToWatch = moviesToWatch;
        this.notifications = notifications;
        this.subscriptions = subscriptions;
        this.likeGenres = likeGenres;
    }

    public User(final User user) {
        Credentials newCredentials = new Credentials(user.getCredentials().getName(),
                user.getCredentials().getPassword(), user.getCredentials().getAccountType(),
                user.getCredentials().getCountry(), user.getCredentials().getBalance());
        setCredentials(newCredentials);
        setTokensCount(user.getTokensCount());
        setNumFreePremiumMovies(user.getNumFreePremiumMovies());
        setPurchasedMovies(deepCopyForArray(user.getPurchasedMovies()));
        setWatchedMovies(deepCopyForArray(user.getWatchedMovies()));
        setLikedMovies(deepCopyForArray(user.getLikedMovies()));
        setRatedMovies(deepCopyForArray(user.getRatedMovies()));
        setMoviesToWatch(deepCopyForArray(user.getMoviesToWatch()));
        setNotifications(deepCopyForArray1(user.getNotifications()));
        setSubscriptions(user.getSubscriptions());
        setLikeGenres(user.getLikeGenres());
    }

    public User(final Credentials credentials) {
        setCredentials(credentials);
        setTokensCount(0);
        setNumFreePremiumMovies(15);
        setPurchasedMovies(new ArrayList<Movie>());
        setWatchedMovies(new ArrayList<Movie>());
        setLikedMovies(new ArrayList<Movie>());
        setRatedMovies(new ArrayList<Movie>());
        setMoviesToWatch(new ArrayList<Movie>());
        setNotifications(new ArrayList<Notification>());
        setSubscriptions(new ArrayList<String>());
        setLikeGenres(new HashMap<>());
    }

    /**
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

    public ArrayList<Notification> deepCopyForArray1(final ArrayList<Notification> arrayList) {
        ArrayList<Notification> retArray = new ArrayList<>();
        for (Notification notification : arrayList) {
            retArray.add(new Notification(notification));
        }
        return retArray;
    }

    /**
     * @param input  dataBase from Json File
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return arraylist of movies available for the user depending on the country in which he is
     * located
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
        return "User{" +
                "credentials=" + credentials +
                ", tokensCount=" + tokensCount +
                ", numFreePremiumMovies=" + numFreePremiumMovies +
                ", purchasedMovies=" + purchasedMovies +
                ", watchedMovies=" + watchedMovies +
                ", likedMovies=" + likedMovies +
                ", ratedMovies=" + ratedMovies +
                ", moviesToWatch=" + moviesToWatch +
                ", notifications=" + notifications +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
