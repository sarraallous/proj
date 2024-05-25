package com.example.hotels.Controller;

import com.example.hotels.Entities.User;
import com.example.hotels.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private HttpSession httpSession; // Spring's HttpSession abstraction

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addAttributes(Model model) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId != null) {
            User loggedInUser = userService.findById(userId);
            model.addAttribute("loggedInUser", loggedInUser);
        }
    }
    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        User authenticatedUser = userService.authenticate(user.getNom(), user.getMdp());

        if (authenticatedUser != null) {
            // Store user information in session
            httpSession.setAttribute("userId", authenticatedUser.getUserId());
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
        // Remove user information from session on logout
        httpSession.removeAttribute("userId");
        return "redirect:/login";
    }

    @GetMapping("/user")
    public ModelAndView getUserInfo() {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        ModelAndView mav = new ModelAndView();
        if (userId != null) {
            User user = userService.findById(userId);
            if (user != null) {
                mav.setViewName("user");
                mav.addObject("user", user);
            } else {
                mav.setViewName("login");
            }
        } else {
            mav.setViewName("login");
        }
        return mav;
    }

    @GetMapping("/")
    public String index(Model model) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId != null) {
            User loggedInUser = userService.findById(userId);
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "index"; // Assuming your index page is named "index.html"
    }
    @GetMapping("/admin")
    public String admin(Model model) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId != null && userService.isAdmin(userId)) {
            return "admin"; // Ensure you have an admin.html template
        }
        return "redirect:/"; // Redirect non-admin users to the home page
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user"; // Assurez-vous que ce fichier existe
    }
    @GetMapping("/users/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId, RedirectAttributes redirectAttributes) {
        userService.deleteUser(userId);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        return "redirect:/users";
    }



}