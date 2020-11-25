package com.example.petproject.controller;

import com.example.petproject.service.ImageFileService;
import com.example.petproject.service.MailService;
import com.example.petproject.service.PetService;
import com.example.petproject.vo.PetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private PetService petService;

    @Autowired
    private ImageFileService imageFileService;

    @Autowired
    private MailService mailService;

    @GetMapping("/getPetList")
    public List<PetVo> findPetsByOwnerId(@RequestParam int ownerId) {
        return petService.findByOwnerId(ownerId);
    }

    @PostMapping("/imgUpload")
    public String imgUpload(@RequestParam("files") MultipartFile uploadfiles) {
        return  imageFileService.saveImage(uploadfiles);
    }

    @GetMapping("/sendCodeMail")
    public void sendCodeMail(@RequestParam String mail) {
        mailService.sendValidCodeMail(mail);
    }
}
