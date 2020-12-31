package com.example.petproject.controller;

import com.example.petproject.bean.LoginForm;
import com.example.petproject.service.*;
import com.example.petproject.vo.AdoptVo;
import com.example.petproject.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://192.168.0.49:5000", allowCredentials = "true")
public class ApiRestController {

//    @Autowired
//    private PetService petService;
    @Autowired
    private ImageFileService imageFileService;
    @Autowired
    private MailService mailService;
    @Autowired
    private AdoptService adoptService;
    @Autowired
    private ValidCodeService validCodeService;
    @Autowired
    private UserService userService;
//    @Autowired
//    private ShelterService shelterService;
    @Autowired
    private MenuService menuService;

//    @GetMapping("/getPetList")
//    public List<PetVo> findPetsByOwnerId(@RequestParam int ownerId) {
//        return petService.findByOwnerId(ownerId);
//    }
//
    @GetMapping("/test")
    public void test(HttpSession httpSession) {
        System.out.println("httpSession = " + httpSession.getId());
    }

    @PostMapping("/imgUpload")
    public String imgUpload(@RequestParam("files") MultipartFile[] uploadfiles) {
        return imageFileService.saveImage(uploadfiles);
    }

    @PostMapping("/sendCodeMail")
    public void sendCodeMail(@RequestBody LoginForm loginForm) {
        userService.registerUser(loginForm);
        mailService.sendValidCodeMail(loginForm.getMail());
    }

    @PostMapping("/validCode")
    public String validCode(@RequestBody Map<String, String> requestParams) {
        return validCodeService.validCode(requestParams.get("code"));
    }
//
//    @PostMapping("/changePassword")
//    public boolean changePassword(@RequestParam String password1, @RequestParam String password2) {
//        return userService.changePassword(password1, password2);
//    }
//
//    @GetMapping("/logout")
//    public void logout(HttpSession httpSession) {
//        httpSession.invalidate();
//    }
//
//    @GetMapping("/images")
//    public List<ImageVo> findImagesByUserId(@RequestParam long userId) {
//        return imageFileService.findImageByUserId(userId);
//    }
//
    @GetMapping("/moreAdopts")
    public List<AdoptVo> moreAdopts(@RequestParam(required = false, defaultValue = "0") int currentIndex) {
        return adoptService.getAdoptList(currentIndex);
    }
//
//    @GetMapping("/shelterList")
//    public List<Shelter> shelterList(@RequestParam(required = false, defaultValue = "N") String position) {
//        return shelterService.getShelterList(position);
//    }

    @GetMapping("/menuList")
    public ResponseEntity<List<MenuVo>> getMenuList(@RequestParam(required = false, defaultValue = "1") int status,
                                                    @RequestParam(required = false, defaultValue = "2") int level) {
        return new ResponseEntity<>(menuService.findByStatusAndLevel(status, level), HttpStatus.OK);
    }
}
