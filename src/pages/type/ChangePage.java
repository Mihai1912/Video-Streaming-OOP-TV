package pages.type;

import designpattern.factorypattern.PageFactory;
import designpattern.visitorpattern.ChangePageVisitor;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ChangePage {
    private static ChangePage instance = null;

    private ChangePage() {
    }

    /**
     *
     * @return instance of ChangePage
     */
    public static ChangePage getInstance() {
        if (instance == null) {
            instance = new ChangePage();
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
    public void changePage(final Input input, final Action action, final Helper helper) {
        PageFactory pageFactory = new PageFactory();
        ChangePageVisitor changePageVisitor = new ChangePageVisitor();
        if (!pageFactory.getPage(helper.getCurrentPage())
                .accept(changePageVisitor, action.getPage())
                && !action.getPage().equals("logout")) {
            helper.printError();
            return;
        }
        if (action.getPage().equals("movies") || action.getPage().equals("see details")) {
            if (pageFactory.getPage(action.getPage()).actionTaken(input, action, helper)) {
                helper.printOutput();
                helper.setCurrentPage(action.getPage());
                helper.getPageStack().push(action.getPage());
                return;
            }
            helper.printError();
            return;
        }
        if (action.getPage().equals("logout")) {
            if (!helper.getCurrentPage().equals("homePageUnauthentify")) {
                pageFactory.getPage(action.getPage()).actionTaken(input, action, helper);
                return;
            }
            helper.printError();
            return;
        }
        helper.setCurrentPage(action.getPage());
        helper.getPageStack().push(action.getPage());
    }
}
