package com.example.hotels.Controller;

import com.example.hotels.Entities.User;
import com.example.hotels.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        User oauthUser = userService.login(user.getNom(), user.getMdp());

        if (Objects.nonNull(oauthUser)) {
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/register")
    public ModelAndView showRegisterForm() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }


    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
