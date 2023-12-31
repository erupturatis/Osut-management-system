package javaspring.osutappjava.controller;

import javaspring.osutappjava.model.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Controller
public class ClientController {

    @Autowired
    private MyRepository myRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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
