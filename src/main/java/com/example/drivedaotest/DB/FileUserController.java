package com.example.drivedaotest.DB;

import com.example.drivedaotest.entity.FileUser;
import com.example.drivedaotest.storage.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

@RequestMapping()
public class FileUserController {

    private final FileUserRepository fileUserRepository;
    private final StorageService storageService;

    public FileUserController(FileUserRepository fileUserRepository, StorageService storageService) {
        this.fileUserRepository = fileUserRepository;
        this.storageService = storageService;
    }

    @PostMapping("/add")
    public @ResponseBody String addNewUser ( @RequestParam("login") String login
            , @RequestParam("password") String password) {


        FileUser n = new FileUser();
        n.setLogin(login);
        n.setPassword(password);
        fileUserRepository.save(n);
        storageService.createDir(login);
        return "Saved";
    }

}
