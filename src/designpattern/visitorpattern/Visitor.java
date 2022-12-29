package designpattern.visitorpattern;

import pages.Page;
import pages.HomePageUnauthentify;
import pages.RegisterPage;
import pages.MoviesPage;
import pages.UpgradesPage;
import pages.LogoutPage;
import pages.LoginPage;
import pages.HomePageAuthentify;
import pages.SeeDetailsPage;

public interface Visitor {
    /**
     *
     * @param page page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(Page page, String pageNameToChange);

    /**
     *
     * @param homePageUnauthentify page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(HomePageUnauthentify homePageUnauthentify, String pageNameToChange);

    /**
     *
     * @param homePageAuthentify page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(HomePageAuthentify homePageAuthentify, String pageNameToChange);

    /**
     *
     * @param loginPage page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(LoginPage loginPage, String pageNameToChange);

    /**
     *
     * @param logoutPage page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(LogoutPage logoutPage, String pageNameToChange);

    /**
     *
     * @param moviesPage page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(MoviesPage moviesPage, String pageNameToChange);

    /**
     *
     * @param registerPage page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(RegisterPage registerPage, String pageNameToChange);

    /**
     *
     * @param seeDetailsPage page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(SeeDetailsPage seeDetailsPage, String pageNameToChange);

    /**
     *
     * @param upgradesPage page visited
     * @param pageNameToChange the page where you want to navigate
     * @return permission to go to another page from the visited page
     */
    boolean visit(UpgradesPage upgradesPage, String pageNameToChange);
}
