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
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private hotelservice hotelService;

    @GetMapping("/retrieve-all-hotel")
    public String getHotels(Model model) {
        List<Hotel> listHotels = hotelService.findAll();
        model.addAttribute("hotels", listHotels);
        return "hotels"; // Ensure this matches the name of your HTML file
    }


    @PostMapping("/addHotel")
    public String addHotel(@ModelAttribute Hotel hotel, RedirectAttributes redirectAttributes) {
        hotelService.addHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage", "Hôtel ajouté avec succès!");
        return "redirect:/hotel/retrieve-all-hotel"; // Redirection vers la page qui affiche tous les hôtels
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("hotel", new Hotel()); // Hotel est votre classe de modèle
        return "hotel/hotelform"; // Le nom de votre fichier hotelform.html
    }

    @GetMapping("/delete")
    public String deleteHotel(@RequestParam("hotelId") int id) {
        hotelService.DeleteHotel(id);
        return "redirect:/hotel/retrieve-all-hotel";
    }

    @GetMapping("/update")
    public String updateHotel(@RequestParam("hotelId") int id, Model model) {
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotel);
        return "hotel/updatehotel";
    }

    @PostMapping("/updateHotel")
    public String updateHotel(@ModelAttribute Hotel hotel) {
        hotelService.updateHotel(hotel.getHotel_id(), hotel);
        return "redirect:/hotel/retrieve-all-hotel";
    }
}
