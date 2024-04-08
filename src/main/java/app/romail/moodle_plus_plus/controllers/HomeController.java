package app.romail.moodle_plus_plus.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model){
        // Get current time
        LocalTime currentTime = LocalTime.now();

        // Determine if it's morning or evening
        String greeting = currentTime.isBefore(LocalTime.NOON) ? "Good morning" : "Good evening";

        // Pass the greeting and user's name to the view
        model.addAttribute("greeting", greeting);
        model.addAttribute("username", "user"); // Replace "user" with the actual username

        // Add user profile details
        model.addAttribute("profilePicture", "path/to/profile/picture.jpg"); // Replace with the actual path to the profile picture
        model.addAttribute("name", "User Name"); // Replace with the actual user name
        model.addAttribute("email", "user@example.com"); // Replace with the actual user email

        //returns the view
        return "index";
    }
}