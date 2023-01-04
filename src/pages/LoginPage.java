package pages;

import designpattern.visitorpattern.Visitor;
import fileinput.Action;
import fileinput.Input;
import fileinput.User;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public final class LoginPage extends Page {
    private String pageName;

    public LoginPage(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public boolean actionTaken(final Input input, final Action action, final Helper helper) {
        for (User user : helper.getUsers()) {
            if (user.getCredentials().getName().equals(action.getCredentials().getName())
                    && user.getCredentials().getPassword()
                    .equals(action.getCredentials().getPassword())) {
                helper.setCurrentUser(user);
                helper.setCurrentMovieList(new ArrayList<>());
                user.setMoviesToWatch(user.moviesAvailable(input, helper));
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "LoginPage{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
