package fileinput;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Notification {
    private String message;
    private String movieName;

    public Notification(final Notification notification) {
        message = notification.message;
        movieName = notification.movieName;
    }

    public Notification() {
    }
}
