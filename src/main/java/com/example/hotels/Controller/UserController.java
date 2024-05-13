package com.example.hotels.Controller;

import com.example.hotels.Entities.User;
import com.example.hotels.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

   /* @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Assuming you have a signup.html template for the sign-up form
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("user") @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return "signup"; // Return to the sign-up form with validation errors
        }
        if (userService.findById(user.getUserId()) != null) {
            // Email already exists, add error message and return to sign-up form
            result.rejectValue("email", "error.user", "Email already registered");
            return "signup";
        }
        // Encrypt password before saving
        user.setMdp(passwordEncoder.encode(user.getMdp()));
        userService.registerUser(user);
        return "redirect:/login"; // Redirect to the login page after successful registration
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login"; // Assuming you have a login.html template for the login form
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // Invalidate session
        return "redirect:/"; // Redirect to the home page after logout
    }*/
}

