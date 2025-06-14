package com.example.breakout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@SpringBootApplication
@Controller
public class BreakoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakoutApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/start")
    public String startGame(@RequestParam String name,
                            @RequestParam String difficulty,
                            @RequestParam String ballColor,
                            @RequestParam String platformColor,
                            Model model) {
        System.out.println("Player: " + name + ", Difficulty: " + difficulty);
        name = name.isEmpty() ? name : Character.toUpperCase(name.charAt(0)) + name.substring(1);
        String txt = CallingNames.response(name, difficulty);
        model.addAttribute("message", txt);
        model.addAttribute("level", difficulty);
        model.addAttribute("ballColor", ballColor);
        model.addAttribute("platformColor", platformColor);
        return "game";
    }
}
