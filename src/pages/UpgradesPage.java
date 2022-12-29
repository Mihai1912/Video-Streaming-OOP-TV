package pages;

import designpattern.visitorpattern.Visitor;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;

public final class UpgradesPage extends Page {
    private String pageName;

    public UpgradesPage(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public boolean actionTaken(final Input input, final Action action, final Helper helper) {
        if (action.getFeature().equals("buy tokens")) {
            int balance = Integer.parseInt(helper.getCurrentUser().getCredentials().getBalance());
            if (balance >= action.getCount()) {
                helper.getCurrentUser().getCredentials()
                        .setBalance(String.valueOf(balance - action.getCount()));
                helper.getCurrentUser().setTokensCount(action.getCount());
            }
        } else if (action.getFeature().equals("buy premium account")) {
            int tokensCount = helper.getCurrentUser().getTokensCount();
            if (tokensCount >= 10) {
                helper.getCurrentUser().getCredentials().setAccountType("premium");
                helper.getCurrentUser().setTokensCount(tokensCount - 10);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "UpgradesPage{"
                + "pageName='" + pageName + '\''
                + '}';
    }

    @Override
    public boolean accept(final Visitor v, final String a) {
        return v.visit(this, a);
    }
}
