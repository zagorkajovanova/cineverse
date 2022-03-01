package mk.ukim.finki.wp.cineverse.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping
    public String getRegisterPage(Model model){
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("style2", "register.css");
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    //TODO: implement register user
}
