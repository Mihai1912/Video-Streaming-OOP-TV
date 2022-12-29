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

public final class ChangePageVisitor implements Visitor {

    @Override
    public boolean visit(final Page page, final String pageNameToChange) {
        return false;
    }

    @Override
    public boolean visit(final HomePageUnauthentify homePageUnauthentify,
                         final String pageNameToChange) {
        return pageNameToChange.equals("register") || pageNameToChange.equals("login");
    }

    @Override
    public boolean visit(final LoginPage loginPage,
                         final String pageNameToChange) {
        return false;
    }

    @Override
    public boolean visit(final RegisterPage registerPage,
                         final String pageNameToChange) {
        return false;
    }

    @Override
    public boolean visit(final HomePageAuthentify homePageAuthentify,
                         final String pageNameToChange) {
        return pageNameToChange.equals("logout") || pageNameToChange.equals("movies")
                || pageNameToChange.equals("upgrades");
    }

    @Override
    public boolean visit(final MoviesPage moviesPage,
                         final String pageNameToChange) {
        return pageNameToChange.equals("homePageAuthentify") || pageNameToChange.equals("logout")
                || pageNameToChange.equals("see details") || pageNameToChange.equals("movies");
    }

    @Override
    public boolean visit(final SeeDetailsPage seeDetailsPage,
                         final String pageNameToChange) {
        return pageNameToChange.equals("homePageAuthentify") || pageNameToChange.equals("movies")
                || pageNameToChange.equals("upgrades") || pageNameToChange.equals("see details");
    }

    @Override
    public boolean visit(final UpgradesPage upgradesPage,
                         final String pageNameToChange) {
        return pageNameToChange.equals("homePageAuthentify") || pageNameToChange.equals("movies")
                || pageNameToChange.equals("logout");
    }

    @Override
    public boolean visit(final LogoutPage logoutPage, final String pageNameToChange) {
        return false;
    }
}
