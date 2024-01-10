package javaspring.osutappjava.middleware;

import javaspring.osutappjava.model.UserDataModel;
import javaspring.osutappjava.model.service.UserCookieService;
import javaspring.osutappjava.variables.PathsVariables;
import javaspring.osutappjava.variables.ViewVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.user.UserCookieData;

@Component
public class UserMiddlewareAuth {

    @Autowired
    private UserCookieService userAuthService;

    @Autowired
    private UserDataModel studentDataModel;

    @Autowired
    private ViewVariables viewVariables;

    @Autowired
    private PathsVariables pathsVariables;

    public String checkLoginAndAdmin(HttpServletRequest request) {
        boolean isLoggedIn = false;
        boolean userIsAdmin = false;

        Cookie[] cookies = request.getCookies();
        UserCookieData userData = null;
        try {
            userData = userAuthService.getDataFromLoginCookie(cookies);
            isLoggedIn = true;
            userIsAdmin = userData.getIsAdmin();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (!isLoggedIn || !userIsAdmin) {
            return pathsVariables.prefixRedirect() + pathsVariables.getAuthPath();
        }

        return null; // No redirect needed
    }

    public String checkLogin(HttpServletRequest request) {
        boolean isLoggedIn = false;
        boolean userIsAdmin = false;

        Cookie[] cookies = request.getCookies();
        UserCookieData userData = null;
        try {
            userData = userAuthService.getDataFromLoginCookie(cookies);
            isLoggedIn = true;
            userIsAdmin = userData.getIsAdmin();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (!isLoggedIn) {
            return pathsVariables.prefixRedirect() + pathsVariables.getAuthPath();
        }

        return null;
    }

    public String checkLoginAndOwner(HttpServletRequest request, String name) {
        boolean isLoggedIn = false;
        boolean userIsAdmin = false;

        Cookie[] cookies = request.getCookies();
        UserCookieData userData = null;
        try {
            userData = userAuthService.getDataFromLoginCookie(cookies);
            isLoggedIn = true;
            userIsAdmin = userData.getIsAdmin();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (!isLoggedIn || (!userIsAdmin && !userData.getUsername().equalsIgnoreCase(name))) {
            return pathsVariables.prefixRedirect() + pathsVariables.getAuthPath();
        }
        return null; // No redirect needed
    }

    public String checkUserExists(String name) {
        if (!studentDataModel.checkUserExists(name)) {
            return pathsVariables.prefixRedirect() + pathsVariables.getUserAdminPath();
        }
        return null;
    }

    public boolean checkUserIsAdmin(HttpServletRequest request) {
        boolean isLoggedIn = false;
        boolean userIsAdmin = false;

        Cookie[] cookies = request.getCookies();
        UserCookieData userData = null;
        try {
            userData = userAuthService.getDataFromLoginCookie(cookies);
            isLoggedIn = true;
            userIsAdmin = userData.getIsAdmin();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (!isLoggedIn || !userIsAdmin) {
            return false;
        }

        return true;
    }
}
