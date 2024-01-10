package javaspring.osutappjava.controller;

import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.dto.user.UserCookieData;
import javaspring.osutappjava.dto.user.UserDB;
import javaspring.osutappjava.middleware.UserMiddlewareAuth;
import javaspring.osutappjava.model.DepartmentDataModel;
import javaspring.osutappjava.model.UserDataModel;
import javaspring.osutappjava.model.service.UserCookieService;
import javaspring.osutappjava.variables.PathsVariables;
import javaspring.osutappjava.variables.ViewVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private UserDataModel studentDataModel;

    @Autowired
    private UserCookieService userAuthService;

    @Autowired
    private ViewVariables viewVariables;

    @Autowired
    private UserMiddlewareAuth userMiddlewareAuth;

    @Autowired
    private PathsVariables pathsVariables;

    @Autowired
    private DepartmentDataModel departmentDataModel;

    @GetMapping(PathsVariables.USER_ADMIN_PATH)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam Optional<String> sort,
                        @RequestParam Optional<String> filter) {

        boolean sortByAttendance = sort.isPresent() && "attendance".equalsIgnoreCase(sort.get());

        String redirect = userMiddlewareAuth.checkLoginAndAdmin(request);
        UserCookieData userData = userAuthService.getDataFromLoginCookie(request.getCookies());
        if (redirect != null) {
            return redirect;
        }
        model.addAttribute("name", userData.getUsername());

        // handle departments
        List<DepartmentDB> departments = departmentDataModel.getAll();
        model.addAttribute("departments", departments);

        // handle users
        List<UserDB> users = studentDataModel.getUsersWithFilters(filter, sortByAttendance);
        model.addAttribute("users", users);
        model.addAttribute("user_path", PathsVariables.USER_MEMBER_PATH);
        model.addAttribute("department_path", PathsVariables.DEPARTMENT_PATH);

        return viewVariables.getView(ViewVariables.viewEnum.USER_ADMIN_VIEW);
    }

    @GetMapping(PathsVariables.USER_CREATE_PATH)
    public String create(Model model, HttpServletRequest request) {
        String redirect = userMiddlewareAuth.checkLoginAndAdmin(request);
        if (redirect != null) {
            return redirect;
        }

        return viewVariables.getView(ViewVariables.viewEnum.USER_CREATE_VIEW);
    }

    @PostMapping(PathsVariables.USER_CREATE_PATH)
    public String createStudent(@RequestParam String username,
                                @RequestParam String password,
                                RedirectAttributes redirectAttributes) {
        try {
            UserDB newUser = new UserDB();
            newUser.setUser_id(username);
            newUser.setUser_password(password);
            newUser.setIs_admin(false);
            boolean success = studentDataModel.addUser(newUser);

            if (!success) {
                throw new Exception("Error creating student");
            }

            // If the student was successfully created, you might want to add a success message
            redirectAttributes.addFlashAttribute("successMessage", "Student created successfully!");
            // Redirect back to the admin page or to the list of students
            return pathsVariables.prefixRedirect() + pathsVariables.getUserAdminPath();
        } catch (Exception e) {
            // If there was an error, add an error message to be displayed
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating student: " + e.getMessage());
            // Redirect back to the create form
            return pathsVariables.prefixRedirect() + pathsVariables.getUserCreatePath();
        }
    }

}
