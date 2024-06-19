package com.example.drivedaotest.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.drivedaotest.storage.StorageFileNotFoundException;
import com.example.drivedaotest.storage.StorageService;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) {

        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "Вы успешно загрузили " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound() {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/files/{filename:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        try {
            boolean existed = storageService.delete(filename);

            if (existed) {
                return ResponseEntity.status(HttpStatus.OK).body("Deleted " + filename);
            } else{
                return ResponseEntity.status(HttpStatus.OK).body("File not found");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("Error");
        }
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<String> renameFile(@PathVariable String filename){
        try {
            Path newFile = storageService.rename(filename);
            return Files.copy(filename, newFile);

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body("Error");
        }
    }

}

