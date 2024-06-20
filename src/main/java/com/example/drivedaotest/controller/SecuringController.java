package com.example.drivedaotest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecuringController {

    @GetMapping("/secure-home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the home page!");
        return "home"; // Возвращает имя шаблона Thymeleaf (home.html)
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Возвращает имя шаблона Thymeleaf (login.html)
    }
}