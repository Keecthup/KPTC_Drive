package com.example.drivedaotest.DB;

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

    @PostMapping("/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser ( @RequestParam("login") String login
            , @RequestParam("password") String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        FileUser n = new FileUser();
        n.setUserLogin(login);
        n.setUserPassword(password);
        fileUserRepository.save(n);
        storageService.createDir(login);
        return "Saved";
    }

}
