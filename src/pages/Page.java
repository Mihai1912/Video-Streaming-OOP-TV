package pages;

import designpattern.visitorpattern.Visitable;
import designpattern.visitorpattern.Visitor;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Page implements Visitable {
    private String pageName;

    public Page(final String pageName) {
        this.pageName = pageName;
    }

    public Page() {
    }

    /**
     *
     * @param input dataBase from Json File
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     * @return the true value of the performance of the action
     */
    public boolean actionTaken(final Input input, final Action action, final Helper helper) {
        return false;
    }

    /**
     *
     * @return page as a string
     */
    @Override
    public String toString() {
        return "Page{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    /**
     *
     * @param v desired implementation of visitor
     * @param a the parameter with which visitor is called
     * @return the truth value of the visit function
     */
    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
