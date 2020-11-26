package com.example.petproject.controller;

import com.example.petproject.service.*;
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

    @Autowired
    private ValidCodeService validCodeService;

    @Autowired
    private UserService userService;

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

    @PostMapping("/validCode")
    public String validCode(@RequestParam String code) {
        return validCodeService.validCode(code);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestParam String password1, @RequestParam String password2) {
        System.out.println("password1 = " + password1 + " ,password2 = " + password2);
        return userService.changePassword(password1, password2);
    }
}
