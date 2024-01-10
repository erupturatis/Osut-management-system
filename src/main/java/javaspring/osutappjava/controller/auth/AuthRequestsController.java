package javaspring.osutappjava.controller.auth;

import com.zaxxer.hikari.util.SuspendResumeLock;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import javaspring.osutappjava.model.UserDataModel;
import javaspring.osutappjava.variables.PathsVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import javaspring.osutappjava.dto.user.UserAuthCredentials;
import javaspring.osutappjava.dto.user.UserDB;

@Controller
public class AuthRequestsController {

    @Autowired
    private UserDataModel authModel;

    @Autowired
    private PathsVariables pathsVariables;

    @PostMapping(PathsVariables.AUTH_POST_PATH)
    public ResponseEntity<?> login(@RequestBody UserAuthCredentials credentials, HttpServletResponse response) {
        // get data input from user
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        boolean isAuthenticated = false;
        UserDB user = null;
        // matches with database
        try {
            user = authModel.authUser(username, password);
            System.out.println(user);
            if (user == null) {
                System.out.println("User is NULL");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed user NULL");
            } else {
                isAuthenticated = true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }

        if (isAuthenticated) {
            boolean isAdmin = user.isIs_admin();
            // Set the cookie if authentication is successful
            Cookie cookie = new Cookie("login", username + "#" + isAdmin);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.ok("Login successful");

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

}
