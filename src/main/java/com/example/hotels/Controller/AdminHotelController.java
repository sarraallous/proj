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
@RequestMapping("/admin/hotels") // Changed the base request mapping for admin hotels
public class AdminHotelController {

    @Autowired
    private hotelservice hotelService;

    @GetMapping("/retrieve-all-hotel")
    public String getHotels(Model model) {
        List<Hotel> listHotels = hotelService.findAll();
        model.addAttribute("hotels", listHotels);
        return "admin/hotels"; // Assuming "hotels" is your view for listing hotels
    }
    @GetMapping("/add") // Added GET handler method for /add
    public String showAddForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "admin/hotelform";
    }
    @PostMapping("/add") // Changed the mapping to /add
    public String addHotel(@ModelAttribute Hotel hotel, RedirectAttributes redirectAttributes) {
        hotelService.addHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage", "Hotel added successfully!");
        return "redirect:/admin/hotels/retrieve-all-hotel"; // Redirecting to the admin hotels listing
    }

    @GetMapping("/delete/{hotelId}") // Changed the mapping to include path variable
    public String deleteHotel(@PathVariable("hotelId") int id) {
        hotelService.DeleteHotel(id);
        return "redirect:/admin/hotels/retrieve-all-hotel"; // Redirecting to the admin hotels listing
    }

    @GetMapping("/update/{hotelId}") // Changed the mapping to include path variable
    public String updateHotel(@PathVariable("hotelId") int id, Model model) {
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotel);
        return "admin/updatehotel"; // Assuming "updatehotel" is your update hotel form view
    }

    @PostMapping("/update/{hotelId}") // Changed the mapping to include path variable
    public String updateHotel(@PathVariable("hotelId") int id, @ModelAttribute Hotel hotel) {
        hotelService.updateHotel(id, hotel); // Corrected the parameter passed to the service method
        return "redirect:/admin/hotels/retrieve-all-hotel"; // Redirecting to the admin hotels listing
    }
}

