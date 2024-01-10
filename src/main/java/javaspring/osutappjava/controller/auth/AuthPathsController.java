package javaspring.osutappjava.controller.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import javaspring.osutappjava.model.UserDataModel;
import javaspring.osutappjava.variables.ViewVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javaspring.osutappjava.variables.PathsVariables;
import org.springframework.ui.Model;

@Controller
public class AuthPathsController {


    @Autowired
    private ViewVariables viewVariables;

    @Autowired
    private PathsVariables pathsVariables;


    @GetMapping(PathsVariables.LOGOUT_PATH)
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        String redirectLoginView = pathsVariables.prefixRedirect() + pathsVariables.getAuthPath();
        return redirectLoginView;
    }

    @GetMapping(PathsVariables.AUTH_PATH)
    public String login(Model model) {
        String authView = viewVariables.getView(ViewVariables.viewEnum.AUTH_VIEW);
        // path to user
        String pathToUser = pathsVariables.getUserMemberPath();
        model.addAttribute("pathToUser", pathToUser);
        return authView;
    }

}
