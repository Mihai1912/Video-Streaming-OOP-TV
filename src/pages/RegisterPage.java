package pages;

import designpattern.visitorpattern.Visitor;
import fileinput.Action;
import fileinput.Input;
import fileinput.User;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class RegisterPage extends Page {
    private String pageName;

    public RegisterPage(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public boolean actionTaken(final Input input, final Action action, final Helper helper) {
        User user = new User(action.getCredentials());
        input.getUsers().add(user);
        helper.setCurrentUser(user);
        user.setMoviesToWatch(user.moviesAvailable(input, helper));
        return true;
    }

    @Override
    public String toString() {
        return "RegisterPage{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
