package javaspring.osutappjava.model.service;

import jakarta.servlet.http.Cookie;
import javaspring.osutappjava.dto.user.UserDB;
import javaspring.osutappjava.model.StudentDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javaspring.osutappjava.dto.user.UserCookieData;

@Service
public class UserAuthService {

    @Autowired
    private StudentDataModel studentDataModel;

    public UserCookieData searchForLoginCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                try{
                    String username = cookie.getValue().split("#")[0];
                    String userType = cookie.getValue().split("#")[1];
                    return new UserCookieData(username, userType);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        throw new RuntimeException("User not found in cookie");
    }
    public UserData searchUserInCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                try{
                    String username = cookie.getValue().split("#")[0];
                    String userType = cookie.getValue().split("#")[1];
                    if (name.equalsIgnoreCase(username)) {
                        return new UserData(username, userType);
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        throw new RuntimeException("User not found in cookie");
    }
}
