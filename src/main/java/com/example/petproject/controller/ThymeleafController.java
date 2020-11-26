package com.example.petproject.controller;

import com.example.petproject.service.MenuService;
import com.example.petproject.service.PetService;
import com.example.petproject.service.UserService;
import com.example.petproject.vo.PetVo;
import com.example.petproject.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ThymeleafController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;
    @Autowired
    private PetService petService;
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("menu", menuService.getMenu(1, 2));
        return "index";
    }

    @RequestMapping("/menu/userInfo")
    public String userInfo(Model model) {
        UserVo userVo = userService.findById(1);
        model.addAttribute("userVo", userVo);
        return "menu/userInfo";
    }

    @RequestMapping("/menu/myPets")
    public String myPets(Model model) {
        List<PetVo> petVoList = petService.findByOwnerId(1);
        model.addAttribute("petVoList", petVoList);
        return "menu/myPets";
    }

    @RequestMapping("/menu/changePassword")
    public String changePassword(Model model) {
        return "menu/changePassword";
    }
}
