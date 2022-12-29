package pages.type;

import designpattern.factorypattern.PageFactory;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class OnPage {
    private static OnPage instance = null;

    private OnPage() {
    }

    /**
     *
     * @return instance of OnPage
     */
    public static OnPage getInstance() {
        if (instance == null) {
            instance = new OnPage();
        }
        return instance;
    }

    /**
     *
     * @param input dataBase from Json File
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary fields
     */
    public void onPage(final Input input, final Action action, final Helper helper) {
        PageFactory pageFactory = new PageFactory();
        if (helper.getCurrentPage().equals("login") || helper.getCurrentPage().equals("register")) {
            if (action.getFeature().equals("login") || action.getFeature().equals("register")) {
                if (pageFactory.getPage(helper.getCurrentPage())
                        .actionTaken(input, action, helper)) {
                    helper.setCurrentPage("homePageAuthentify");
                    helper.printOutput();
                    return;
                }
                helper.setCurrentPage("homePageUnauthentify");
                helper.printError();
                return;
            }
        }
        if (pageFactory.getPage(helper.getCurrentPage()).actionTaken(input, action, helper)) {
            if (action.getFeature().startsWith("buy")) {
                return;
            }
            helper.printOutput();
            return;
        }
        helper.printError();
    }
}
