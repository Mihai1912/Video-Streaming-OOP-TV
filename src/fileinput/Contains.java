package fileinput;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
public final class Contains {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    @Override
    public String toString() {
        return "Contains{"
                + "actors=" + actors
                + ", genre=" + genre
                + '}';
    }
}
