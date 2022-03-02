package mk.ukim.finki.wp.cineverse.web.controller;

import mk.ukim.finki.wp.cineverse.model.enums.Role;
import mk.ukim.finki.wp.cineverse.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.cineverse.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.cineverse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(Model model){
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("style2", "register.css");
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    //TODO: implement register user
    @PostMapping
    public String register(@RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String birthday,
                           @RequestParam String role,
                           @RequestParam String address,
                           @RequestParam String password,
                           @RequestParam String repeatPassword){

        Role role1;
        if(role.equals("User")){
            role1 = Role.ROLE_USER;
        }else{
            role1 = Role.ROLE_ADMIN;
        }

        try{
            this.userService.register(username,password,repeatPassword,firstname,lastname,birthday,address,email,role1,"");
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }
}
