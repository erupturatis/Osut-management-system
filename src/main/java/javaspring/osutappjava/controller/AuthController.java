package javaspring.osutappjava.controller;

import javaspring.osutappjava.model.service.UserTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import javaspring.osutappjava.dto.UserCredentials;
import javaspring.osutappjava.model.AuthModel;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AuthController {

    @Autowired
    private AuthModel authModel;

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials credentials, HttpServletResponse response) {
        boolean isAdmin = true;
        // Authentication logic here
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        // matches with database
        UserTypeEnum userType = authModel.authUser(username, password);
        boolean isAuthenticated = userType != UserTypeEnum.NONE;

        if (isAuthenticated) {
            // Set the cookie if authentication is successful
            Cookie cookie = new Cookie("login", username + "#" + userType);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok("User logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

}
