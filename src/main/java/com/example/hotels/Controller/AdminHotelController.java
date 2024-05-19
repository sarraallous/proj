package com.example.hotels.Controller;

import com.example.hotels.Entities.Hotel;
import com.example.hotels.Services.hotelservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/hotels")
public class AdminHotelController {

    @Autowired
    private hotelservice hotelService;

    @GetMapping("/retrieve-all-hotel")
    public String getHotels(Model model) {
        List<Hotel> listHotels = hotelService.findAll();
        model.addAttribute("hotels", listHotels);
        return "admin/hotels";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "admin/hotelform";
    }

    @PostMapping("/add")
    public String addHotel(@ModelAttribute Hotel hotel, RedirectAttributes redirectAttributes) {
        hotelService.addHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage", "Hotel added successfully!");
        return "redirect:/admin/hotels/retrieve-all-hotel";
    }

    @GetMapping("/delete/{hotelId}")
    public String deleteHotel(@PathVariable("hotelId") int id) {
        hotelService.deleteHotel(id);
        return "redirect:/admin/hotels/retrieve-all-hotel";
    }

    @GetMapping("/update/{hotelId}")
    public String updateHotel(@PathVariable("hotelId") int id, Model model) {
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotel);
        return "admin/updatehotel";
    }

    @PostMapping("/update/{hotelId}")
    public String updateHotel(@PathVariable("hotelId") int id, @ModelAttribute Hotel hotel) {
        hotelService.updateHotel(id, hotel); // Ensure this method exists and works correctly
        return "redirect:/admin/hotels/retrieve-all-hotel";
    }

}
