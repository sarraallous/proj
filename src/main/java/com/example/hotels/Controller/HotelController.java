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
@RequestMapping("/hotels") // Changed the request mapping
public class HotelController {

    @Autowired
    private hotelservice hotelService;

    @GetMapping("/retrieve-all-hotel") // Changed the mapping to /retrieve-all-hotel
    public String getHotels(Model model) {
        List<Hotel> listHotels = hotelService.findAll();
        model.addAttribute("hotels", listHotels);
        return "hotels"; // Adjusted the return URL
    }

    @GetMapping("/add") // Added GET handler method for /add
    public String showAddForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotelform";
    }

    @PostMapping("/add") // Changed the mapping to /add
    public String addHotel(@ModelAttribute Hotel hotel, RedirectAttributes redirectAttributes) {
        hotelService.addHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage", "Hotel added successfully!");
        return "redirect:/hotels/retrieve-all-hotel"; // Adjusted the redirect URL
    }

    @GetMapping("/delete/{hotelId}") // Changed the mapping to include path variable
    public String deleteHotel(@PathVariable("hotelId") int id) {
        hotelService.deleteHotel(id);
        return "redirect:/hotels/retrieve-all-hotel"; // Adjusted the redirect URL
    }

    @GetMapping("/update/{hotelId}") // Changed the mapping to include path variable
    public String updateHotel(@PathVariable("hotelId") int id, Model model) {
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotel);
        return "updatehotel"; // Adjusted the return URL
    }

    @PostMapping("/update/{hotelId}") // Changed the mapping to include path variable
    public String updateHotel(@PathVariable("hotelId") int id, @ModelAttribute Hotel hotel) {
        hotelService.updateHotel(id, hotel); // Corrected the parameter passed to the service method
        return "redirect:/hotels/retrieve-all-hotel"; // Adjusted the redirect URL
    }
}
