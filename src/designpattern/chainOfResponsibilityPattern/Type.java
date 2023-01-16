package designpattern.chainOfResponsibilityPattern;

import fileinput.Action;
import fileinput.Input;
import helper.Helper;

public abstract class Type {
    protected Type nextType;

    public Type(final Type nextType) {
        this.nextType = nextType;
    }

    public void doAction(final Input input, final Action action, final Helper helper) {
    };
}
