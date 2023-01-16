package designpattern.chainOfResponsibilityPattern;

import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import pages.type.OnPage;

public final class OnPageType extends Type {
    public OnPageType(final Type nextType) {
        super(nextType);
    }

    @Override
    public void doAction(final Input input, final Action action, final Helper helper) {
        if (action.getType().equals("on page")) {
            OnPage.getInstance().onPage(input, action, helper);
        } else {
            nextType.doAction(input, action, helper);
        }
    }
}
