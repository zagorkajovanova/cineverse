package mk.ukim.finki.wp.cineverse.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileDashboard {

    @GetMapping("/{id}/dashboard")
    public String getProfileDashboard(@PathVariable String id, Model model){

        model.addAttribute("style1", "header-and-footer.css");
        model.addAttribute("bodyContent", "user-profile");
        return "master-template";
    }
}
