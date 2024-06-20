package com.example.drivedaotest.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

@RequestMapping(path="/geek")
public class FileUserController {

    @Autowired
    private FileUserRepository fileUserRepository;


}
