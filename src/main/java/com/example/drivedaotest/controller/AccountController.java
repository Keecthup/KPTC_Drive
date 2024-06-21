package com.example.drivedaotest.controller;

import com.example.drivedaotest.DB.FileUserRepository;
import com.example.drivedaotest.entity.FileUser;
import com.example.drivedaotest.entity.RegisterDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private FileUserRepository fileUserRepository;


    @GetMapping("/register")
    public String register(Model model){
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(
        Model model, @Valid @ModelAttribute RegisterDto registerDto, BindingResult result){

        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            result.addError(new FieldError("registerDto","confirmPassword", "Password and Confirm Password do not math"));
        }

        FileUser fileUser = fileUserRepository.findByLogin(registerDto.getLogin());
        if (fileUser != null){
            result.addError(new FieldError("registerDto","login", "Login is already used"));
        }

        if (result.hasErrors()){
            return "register";
        }

        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            FileUser newFileUser = new FileUser();
            newFileUser.setName(registerDto.getName());
            newFileUser.setSurname(registerDto.getSurname());
            newFileUser.setMiddleName(registerDto.getMidllename());
            newFileUser.setLogin(registerDto.getLogin());
            newFileUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            fileUserRepository.save(newFileUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        } catch (Exception e){
            result.addError(new FieldError("registerDto", "firstName", e.getMessage()));
        }


        return "register";
    }
}
