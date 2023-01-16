package designpattern.chainOfResponsibilityPattern;

import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import pages.type.ChangePage;

public final class ChangePageType extends Type {
    public ChangePageType(final Type nextType) {
        super(nextType);
    }

    @Override
    public void doAction(final Input input, final Action action, final Helper helper) {
        if (action.getType().equals("change page")) {
            ChangePage.getInstance().changePage(input, action, helper);
        } else {
            nextType.doAction(input, action, helper);
        }
    }
}
