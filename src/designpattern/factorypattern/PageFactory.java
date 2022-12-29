package designpattern.factorypattern;

import pages.Page;
import pages.HomePageUnauthentify;
import pages.RegisterPage;
import pages.MoviesPage;
import pages.UpgradesPage;
import pages.LogoutPage;
import pages.LoginPage;
import pages.HomePageAuthentify;
import pages.SeeDetailsPage;

public final class PageFactory {
    /**
     *
     * @param pageName the name of the page we want to create
     * @return page we want to create
     */
    public Page getPage(final String pageName) {
        switch (pageName) {
            case "login" -> {
                LoginPage loginPage = new LoginPage(pageName);
//                System.out.println(loginPage);
                return loginPage;
            }
            case "logout" -> {
                LogoutPage logoutPage = new LogoutPage(pageName);
//                System.out.println(logoutPage);
                return logoutPage;
            }
            case "register" -> {
                RegisterPage registerPage = new RegisterPage(pageName);
//                System.out.println(registerPage);
                return registerPage;
            }
            case "see details" -> {
                SeeDetailsPage seeDetailsPage = new SeeDetailsPage(pageName);
//                System.out.println(seeDetailsPage);
                return seeDetailsPage;
            }
            case "upgrades" -> {
                UpgradesPage upgradesPage = new UpgradesPage(pageName);
//                System.out.println(upgradesPage);
                return upgradesPage;
            }
            case "movies" -> {
                MoviesPage moviesPage = new MoviesPage(pageName);
//                System.out.println(moviesPage);
                return moviesPage;
            }
            case "homePageAuthentify" -> {
                HomePageAuthentify homePageAuthentify = new HomePageAuthentify(pageName);
//                System.out.println(homePageAuthenticated);
                return homePageAuthentify;
            }
            default -> {
                HomePageUnauthentify homePageUnauthentify = new HomePageUnauthentify(pageName);
//                System.out.println(homePageUnauthenticated);
                return homePageUnauthentify;
            }
        }
    }
}
