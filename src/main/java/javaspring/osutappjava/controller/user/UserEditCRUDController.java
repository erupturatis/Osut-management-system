package javaspring.osutappjava.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.model.StudentDataModel;
import javaspring.osutappjava.model.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserEditCRUDController {

    @Autowired
    private StudentDataModel studentDataModel;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/{name}/remove_department/{departmentId}")
    public String removeDepartment(@PathVariable String name, @PathVariable String departmentId, Model model, HttpServletRequest request) {
        studentDataModel.removeDepartmentFromUser(name, departmentId);
        return "redirect:/{name}/edit";
    }

    @GetMapping("/{name}/remove_project/{projectId}")
    public String removeProject(@PathVariable String name, @PathVariable String projectId, Model model, HttpServletRequest request) {
        studentDataModel.removeProjectFromUser(name, projectId);
        return "redirect:/{name}/edit";
    }

    @GetMapping("/{name}/add_department/{departmentId}")
    public String addDepartment(@PathVariable String name, @PathVariable String departmentId, Model model, HttpServletRequest request) {
        studentDataModel.addDepartmentToUser(name, departmentId);
        return "redirect:/{name}/edit";
    }

    @GetMapping("/{name}/add_project/{projectId}")
    public String addProject(@PathVariable String name, @PathVariable String projectId, Model model, HttpServletRequest request) {
        studentDataModel.addProjectToUser(name, projectId);
        return "redirect:/{name}/edit";
    }

    @PostMapping("/{name}/delete")
    public String delete(@PathVariable String name, Model model, HttpServletRequest request) {
        studentDataModel.deleteUser(name);
        return "redirect:/admin";
    }

    @PostMapping("/{name}/new_user_id")
    public String changeUserId(@PathVariable String name, @RequestParam("newUsername") String newUsername, Model model, HttpServletRequest request) {
        studentDataModel.changeUserId(name, newUsername);
        return "redirect:/{name}/edit";
    }

}
