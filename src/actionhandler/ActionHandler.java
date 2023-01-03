package actionhandler;

import pages.type.*;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;
public final class ActionHandler {
    /**
     *
     * @param input dataBase from Json File
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     */
    public void getType(final Input input, final Action action, final Helper helper) {
        if ("change page".equals(action.getType())) {
            ChangePage.getInstance().changePage(input, action, helper);
        } else if ("on page".equals(action.getType())) {
            OnPage.getInstance().onPage(input, action, helper);
        } else if ("database".equals(action.getType())) {
            Database.getInstance().database(input, action, helper);
        } else if ("back".equals(action.getType())){
            Back.getInstance().back(input, action, helper);
        }
    }

    /**
     *
     * @param input dataBase from Json File
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary
     *                fields
     */
    public void doAction(final Input input, final Action action, final Helper helper) {
        getType(input, action, helper);
    }
}
