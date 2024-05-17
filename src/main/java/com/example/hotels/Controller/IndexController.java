package com.example.hotels.Controller;

import com.example.hotels.Services.hotelservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private hotelservice hotService;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index()
    {
        return "index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "about"; // Assuming you have an "about.html" file in your templates directory
    }
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String events() {
        return "events"; // Assuming you have an "about.html" file in your templates directory
    }
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact"; // Assuming you have an "about.html" file in your templates directory
    }
    @RequestMapping(value = "/signupform", method = RequestMethod.GET)
    public String signupform() {
        return "register"; // Assuming you have an "about.html" file in your templates directory
    }

    @RequestMapping(value = "/loginform", method = RequestMethod.GET)
    public String loginform() {
        return "login"; // Assuming you have an "about.html" file in your templates directory
    }
}
