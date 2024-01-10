package javaspring.osutappjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DefaultsController {


    @GetMapping("/")
    public String index(HttpServletRequest request) {
        // Check for a cookie named 'loginCookie' (or whatever your login cookie is named)
        return "redirect:/auth-view";
    }

    @RequestMapping(value = "/**")
    @ResponseBody
    public String fallbackMethod() {
        return "Path not recognized. Please check your URL and try again.";
    }

}
