package designpattern.chainOfResponsibilityPattern;

import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import pages.type.Database;

public final class DatabaseType extends Type {
    public DatabaseType(final Type nextType) {
        super(nextType);
    }

    @Override
    public void doAction(final Input input, final Action action, final Helper helper) {
        if (action.getType().equals("database")) {
            Database.getInstance().database(input, action, helper);
        } else {
            nextType.doAction(input, action, helper);
        }
    }
}
