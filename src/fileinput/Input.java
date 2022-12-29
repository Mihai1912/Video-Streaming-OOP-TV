package fileinput;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
public final class Input {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<Action> actions;

    @Override
    public String toString() {
        return "Input{"
                + "users=" + users
                + ", movies=" + movies
                + ", actions=" + actions
                + '}';
    }
}
