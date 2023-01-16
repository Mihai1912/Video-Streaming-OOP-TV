package actionhandler;

import designpattern.chainOfResponsibilityPattern.*;
import pages.type.ChangePage;
import pages.type.Back;
import pages.type.Database;
import pages.type.OnPage;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;

public final class ActionHandler {
    /**
     * @param input  dataBase from Json File
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     */
    public void getType(final Input input, final Action action, final Helper helper) {
        ChangePageType changePageType =
                new ChangePageType(new OnPageType(new DatabaseType(new BackType(null))));
        changePageType.doAction(input, action, helper);
    }

    /**
     * @param input  dataBase from Json File
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary
     *               fields
     */
    public void doAction(final Input input, final Action action, final Helper helper) {
        getType(input, action, helper);
    }
}
