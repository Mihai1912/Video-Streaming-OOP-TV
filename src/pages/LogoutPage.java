package pages;

import designpattern.visitorpattern.Visitor;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public final class LogoutPage extends Page {
    private String pageName;

    public LogoutPage(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public boolean actionTaken(final Input input, final Action action, final Helper helper) {
        helper.setCurrentUser(null);
        helper.setCurrentMovieList(new ArrayList<>());
        helper.setCurrentPage("homePageUnauthentify");
        helper.setSeeDetailsAfterFilter(0);
        return false;
    }

    @Override
    public String toString() {
        return "LogoutPage{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
