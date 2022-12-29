package fileinput;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@JsonIgnoreProperties(value = {"moviesToWatch", "seeDetails"})
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
    private ArrayList<String> notifications;

    public User() {
    }

    public User(final Credentials credentials, final Integer tokensCount,
                final Integer numFreePremiumMovies, final ArrayList<Movie> purchasedMovies,
                final ArrayList<Movie> watchedMovies, final ArrayList<Movie> likedMovies,
                final ArrayList<Movie> ratedMovies, final ArrayList<Movie> moviesToWatch,
                final ArrayList<String> notifications) {
        this.credentials = credentials;
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.likedMovies = likedMovies;
        this.ratedMovies = ratedMovies;
        this.moviesToWatch = moviesToWatch;
        this.notifications = notifications;
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
        setNotifications(user.getNotifications());
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
        setNotifications(new ArrayList<String>());
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
        return "User{"
                + "credentials=" + credentials
                + ", tokensCount=" + tokensCount
                + ", numFreePremiumMovies=" + numFreePremiumMovies
                + ", purchasedMovies=" + purchasedMovies
                + ", watchedMovies=" + watchedMovies
                + ", likedMovies=" + likedMovies
                + ", ratedMovies=" + ratedMovies
                + ", moviesToWatch=" + moviesToWatch
                + '}';
    }
}
