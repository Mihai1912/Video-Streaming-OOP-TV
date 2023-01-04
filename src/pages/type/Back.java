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
public final class Back {
    private static Back instance = null;

    private Back() {
    }

    /**
     * @return instance of Back
     */
    public static Back getInstance() {
        if (instance == null) {
            instance = new Back();
        }
        return instance;
    }

    /**
     * @param input  dataBase from Json File
     * @param action the action to be performed at this moment
     * @param helper the class in which the current user is stored, the list of current movies and
     *               several auxiliary
     *               fields
     */
    public void back(final Input input, final Action action, final Helper helper) {

        if (helper.getPageStack() == null || helper.getPageStack().isEmpty()) {
            helper.printError();
            return;
        }
        if (helper.getPageStack().size() < 2) {
            return;
        }

        PageFactory pageFactory = new PageFactory();
        ChangePageVisitor changePageVisitor = new ChangePageVisitor();
        String crtPage = helper.getPageStack().pop();
        String previousPage = helper.getPageStack().peek();

        if (crtPage.equals("homePageAuthentify")) {
            helper.printError();
            helper.getPageStack().push("homePageAuthentify");
            return;
        }

        if (previousPage.equals("login") || previousPage.equals("register")) {
            helper.printError();
            return;
        }

        if (previousPage.equals("movies")) {
            if (pageFactory.getPage(previousPage).actionTaken(input, action, helper)) {
                helper.setCurrentPage(previousPage);
                helper.printOutput();
                return;
            }
            helper.printError();
        }

        helper.setCurrentPage(previousPage);
    }
}
