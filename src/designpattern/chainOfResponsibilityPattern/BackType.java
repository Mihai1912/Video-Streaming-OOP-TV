package designpattern.chainOfResponsibilityPattern;

import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import pages.type.Back;

public final class BackType extends Type {
    public BackType(final Type nextType) {
        super(nextType);
    }

    @Override
    public void doAction(final Input input, final Action action, final Helper helper) {
        if (action.getType().equals("back")) {
            Back.getInstance().back(input, action, helper);
        } else {
            return;
        }
    }
}
