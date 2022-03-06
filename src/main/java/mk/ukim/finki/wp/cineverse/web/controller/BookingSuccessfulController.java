package mk.ukim.finki.wp.cineverse.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/success")
public class BookingSuccessfulController {

    @GetMapping()
    public String getBookingSuccessPage(Model model){

        model.addAttribute("style1","header-and-footer.css");
        model.addAttribute("pageTitle", "Booking successful");
        model.addAttribute("bodyContent", "booking-successful");
        return "master-template";
    }
}
