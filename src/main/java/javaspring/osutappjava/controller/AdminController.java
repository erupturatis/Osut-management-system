package javaspring.osutappjava.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.model.StudentDataModel;
import javaspring.osutappjava.model.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private StudentDataModel studentDataModel;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/admin")
    public String index(Model model, HttpServletRequest request,
                        @RequestParam Optional<String> sort,
                        @RequestParam Optional<String> filter) {

        boolean sortByAttendance = sort.isPresent() && "attendance".equalsIgnoreCase(sort.get());

        boolean isLoggedIn = false;
        String userType = null;

        Cookie[] cookies = request.getCookies();
        UserData userData = null;
        try{
            userData = userAuthService.searchForLoginCookie(cookies);
            isLoggedIn = true;
            userType = userData.getUserType();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if(!isLoggedIn) {
            return "redirect:/login";
        }
        if(!userType.equalsIgnoreCase("admin")) {
            return "redirect:/login";
        }

        // handle departments
        List<Department> departments = studentDataModel.getDepartments();
        model.addAttribute("departments", departments);

        // handle users
        List<User> users = studentDataModel.getUsers(filter, sortByAttendance);
        model.addAttribute("users", users);

        return "home-admin";
    }

    @GetMapping("/create")
    public String create(Model model, HttpServletRequest request) {
        boolean isLoggedIn = false;
        String userType = null;

        Cookie[] cookies = request.getCookies();
        UserData userData = null;
        try{
            userData = userAuthService.searchForLoginCookie(cookies);
            isLoggedIn = true;
            userType = userData.getUserType();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if(!isLoggedIn) {
            return "redirect:/login";
        }
        if(!userType.equalsIgnoreCase("admin")) {
            return "redirect:/login";
        }
        return "create";
    }

    @PostMapping("/create-student")
    public String createStudent(@RequestParam String username,
                                @RequestParam String password,
                                RedirectAttributes redirectAttributes) {

        try {
            UserDB newUser = new UserDB();
            newUser.setUser_id(username);
            newUser.setUser_password(password);
            newUser.setUser_type(false);
            boolean success = studentDataModel.addUser(newUser);

            if(!success) {
                throw new Exception("Error creating student");
            }

            // If the student was successfully created, you might want to add a success message
            redirectAttributes.addFlashAttribute("successMessage", "Student created successfully!");

            // Redirect back to the admin page or to the list of students
            return "redirect:/admin";
        } catch (Exception e) {
            // If there was an error, add an error message to be displayed
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating student: " + e.getMessage());

            // Redirect back to the create form
            return "redirect:/create";
        }
    }

}
