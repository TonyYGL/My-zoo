package com.example.petproject.controller;

import com.example.petproject.bean.LineUser;
import com.example.petproject.bean.LoginForm;
import com.example.petproject.service.*;
import com.example.petproject.vo.PetVo;
import com.example.petproject.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private AdoptService adoptService;
    @Autowired
    private ShelterService shelterService;

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute("errorMessage") String errorMessage) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("lineAuth", lineLoginService.lineAuth());
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/login2")
    public String login2(Model model, @ModelAttribute("errorMessage") String errorMessage) {
        return "login2";
    }

    @PostMapping("/formLogin")
    public RedirectView formLogin(@ModelAttribute LoginForm loginForm, HttpSession httpSession,  RedirectAttributes attributes) {
        Optional<UserVo> optionalUserVo = userService.findUserInfo(loginForm.getAccount(), loginForm.getPassword());
        if (optionalUserVo.isPresent()) {
            UserVo userVo = optionalUserVo.get();
            httpSession.setAttribute("loginFlag", true);
            httpSession.setAttribute("lineUser", new LineUser(userVo.getName(), null, userVo.getEmail()));
        } else {
            attributes.addAttribute("errorMessage", "找不到使用者, 請確認帳號密碼");
            return new RedirectView("login");
        }
        return new RedirectView("index");
    }

    /**
     * @param
     * @param code Authorization code used to get an access token. Valid for 10 minutes.
     *             This authorization code can only be used once.
     * @return
     */
    @GetMapping("/line-login")
    public RedirectView lineLogin(HttpSession httpSession, RedirectAttributes attributes, @RequestParam(required = false) String code,
                                  @RequestParam(required = false) String error) {
        if (code != null) {
            LineUser lineUser = lineLoginService.getLineUserInfo(code);
            httpSession.setAttribute("lineUser", lineUser);
            httpSession.setAttribute("loginFlag", true);
            attributes.addFlashAttribute("lineUser", lineUser);
        } else if (error != null) {
            attributes.addAttribute("errorMessage", error);
            return new RedirectView("login");
        }
        return new RedirectView("index");
    }

    @RequestMapping("/index")
    public String index(HttpSession httpSession, Model model, @ModelAttribute("lineUser") LineUser lineUser) {
        if (httpSession.getAttribute("loginFlag") != null) {
            if (httpSession.getAttribute("lineUser") != null) {
                lineUser = (LineUser) httpSession.getAttribute("lineUser");
            }
        } else {
            return "redirect:login";
        }
        model.addAttribute("menu", menuService.getMenu(1, 2));
        model.addAttribute("lineUser", lineUser);
        return "index";
    }

    @RequestMapping("/menu/userInfo")
    public String userInfo(Model model) {
        Optional<UserVo> optionalUserVo = userService.findById(1);
        model.addAttribute("userVo", optionalUserVo.get());
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

    @RequestMapping("/menu/photoAlbum")
    public String photoAlbum(Model model) {
        return "menu/photoAlbum";
    }

    @GetMapping("/menu/adopt")
    public String adopt(Model model) {
        model.addAttribute("adoptVoList", adoptService.getAdoptList(0));
        return "menu/adopt";
    }

    @GetMapping("/menu/shelter")
    public String shelter(Model model) {
        model.addAttribute("shelterList", shelterService.getShelterList("N"));
        return "menu/shelter";
    }
}
