package pages.type;

import designpattern.factorypattern.PageFactory;
import designpattern.visitorpattern.ChangePageVisitor;
import fileinput.Action;
import fileinput.Input;
import helper.Helper;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter
@Setter
public class Back {
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

    public void back(Input input, Action action, Helper helper) {

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

        if (pageFactory.getPage(crtPage).accept(changePageVisitor, previousPage)) {
            helper.setCurrentPage(previousPage);
        }


        if (helper.getCurrentPage().equals("movies")) {
            if (pageFactory.getPage(helper.getCurrentPage()).actionTaken(input, action, helper)) {
                helper.printOutput();
                return;
            }
            helper.printError();
        }
    }
}
