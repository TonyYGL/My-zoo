package com.example.petproject.controller;

import com.example.petproject.bean.LineUser;
import com.example.petproject.service.LineLoginService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    @Autowired
    private LineLoginService lineLoginService;

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    /**
     * @param
     * @param code Authorization code used to get an access token. Valid for 10 minutes.
     *             This authorization code can only be used once.
     * @return
     */
    @GetMapping("/line-login")
    public RedirectView lineLogin(HttpSession httpSession, RedirectAttributes attributes, @RequestParam String code) {
        LineUser lineUser = lineLoginService.getLineUserInfo(code);
        httpSession.setAttribute("lineUser", lineUser);
        attributes.addFlashAttribute("lineUser", lineUser);
        return new RedirectView("index");
    }

    @RequestMapping("/index")
    public String index(HttpSession httpSession, Model model, @ModelAttribute("lineUser") LineUser lineUser) {
        if (lineUser.getName() == null) {
            if (httpSession.getAttribute("lineUser") != null) {
                lineUser = (LineUser) httpSession.getAttribute("lineUser") ;
            }
        }
        model.addAttribute("menu", menuService.getMenu(1, 2));
        model.addAttribute("lineUser", lineUser);
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
