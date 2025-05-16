package com.example.breakout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@SpringBootApplication
@Controller
public class BreakoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakoutApplication.class, args);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "This is Breakout GAME APP!");
        return "index";
    }
}
