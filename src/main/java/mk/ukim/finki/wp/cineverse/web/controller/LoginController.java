package mk.ukim.finki.wp.cineverse.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("style2", "login.css");
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }
}
