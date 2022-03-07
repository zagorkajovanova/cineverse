package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.User;
import mk.ukim.finki.wp.cineverse.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.cineverse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("style1", "header-footer.css");
        model.addAttribute("style2", "login.css");
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user = null;
        try{
            user = this.userService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }catch (InvalidUserCredentialsException exception){
            return "redirect:/login";
        }
    }
}
