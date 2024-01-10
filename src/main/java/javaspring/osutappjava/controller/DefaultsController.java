package javaspring.osutappjava.controller;

import javaspring.osutappjava.model.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class DefaultsController {


    @Autowired
    private MyRepository myRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        // Check for a cookie named 'loginCookie' (or whatever your login cookie is named)
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("login".equals(cookie.getName())) {
                    // gets the name of the user and redirects to /{name}
                    return "redirect:/" + cookie.getValue();
                }
            }
        }
        // No login cookie found, redirect to login
        return "redirect:/login";
    }



    @GetMapping("/test-db-connection")
    public ResponseEntity<String> testDatabaseConnection() {
        boolean isConnected = myRepository.isDatabaseConnected();
        if (isConnected) {
            return ResponseEntity.ok("Database Connection Successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database Connection Failed");
        }
    }
}
