package javaspring.osutappjava.model.service;

import jakarta.servlet.http.Cookie;
import javaspring.osutappjava.model.UserDataModel;
import javaspring.osutappjava.variables.UserVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javaspring.osutappjava.dto.user.UserCookieData;

@Service
public class UserCookieService {

    @Autowired
    private UserDataModel studentDataModel;
    @Autowired
    private UserVariables userVariables;

    public UserCookieData getDataFromLoginCookie(Cookie[] cookies) {
        if (cookies == null) {
            throw new RuntimeException("No cookies found");
        }
        for (Cookie cookie : cookies) {
            String username = cookie.getValue().split("#")[0];
            boolean userIsAdmin = cookie.getValue().split("#")[1].equalsIgnoreCase("true");
            return new UserCookieData(username, userIsAdmin);
        }
        throw new RuntimeException("User not found in cookie");
    }

}
