package javaspring.osutappjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javaspring.osutappjava.variables.PathsVariables;


@Controller
public class DefaultsController {

    @Autowired
    private PathsVariables pathsVariables;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        // Check for a cookie named 'loginCookie' (or whatever your login cookie is named)
        return pathsVariables.prefixRedirect() + pathsVariables.getAuthPath();
    }

    @RequestMapping(value = "/**")
    public String fallbackMethod() {
        return pathsVariables.prefixRedirect() + pathsVariables.getAuthPath();
    }

}
