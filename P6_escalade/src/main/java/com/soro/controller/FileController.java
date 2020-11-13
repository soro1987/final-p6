package com.soro.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.soro.storage.StorageService;

//Controlleur qui gére l'ajout d'images
@Controller
@CrossOrigin("*")
public class FileController {

    private StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    
    
    @GetMapping("/upload-file")
    public String uploadFile() {
   
        return "upload";
    }
    
    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("imageUrl") MultipartFile imageUrl) {
        String name = storageService.store(imageUrl);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(name)
                .toUriString();

        return "redirect:/displaySite";
    }
    
  
   
}