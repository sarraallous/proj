package com.example.hotels.Controller;

import com.example.hotels.Entities.Chambre;
import com.example.hotels.Entities.Hotel;
import com.example.hotels.Services.ChambreService;
import com.example.hotels.Services.hotelservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chambre")
public class ChambreController {

    @Autowired
    private ChambreService chambreService;

    @Autowired
    private hotelservice hotelService;

    @GetMapping("/all")
    public String getChambres(Model model) {
        List<Chambre> listChambre = chambreService.findAll();
        model.addAttribute("listChambre", listChambre);
        return "rooms"; // Assumes there is a rooms.html Thymeleaf template
    }

    @GetMapping("/hotel/{hotelId}")
    public String getChambresByHotelId(@PathVariable int hotelId, Model model) {
        Hotel hotel = hotelService.findById(hotelId);
        if (hotel != null) {
            List<Chambre> listChambre = chambreService.findRoomsByHotel(hotel);
            System.out.println("Hotel ID: " + hotelId + ", Rooms Found: " + listChambre.size());
            model.addAttribute("listChambre", listChambre);
            model.addAttribute("hotel", hotel);
            return "rooms"; // Ensure the correct template is used
        } else {
            return "redirect:/chambre/all"; // Redirect if hotel not found
        }
    }

    @GetMapping("/{id}")
    public String getChambreById(@PathVariable int id, Model model) {
        Chambre chambre = chambreService.findById(id);
        if (chambre != null) {
            model.addAttribute("chambre", chambre);
            return "room"; // Assumes there is a room.html Thymeleaf template
        } else {
            return "redirect:/chambre/all"; // Redirect if not found
        }
    }

    @GetMapping("/add")
    public String showAddChambreForm(Model model) {
        model.addAttribute("chambre", new Chambre());
        return "addChambre"; // Assumes there is an addChambre.html Thymeleaf template
    }

    @PostMapping("/add")
    public String addChambre(@ModelAttribute Chambre chambre, Model model) {
        chambreService.addChambre(chambre);
        return "redirect:/chambre/all"; // Redirect to the list of chambres after adding
    }

    @GetMapping("/update/{id}")
    public String showUpdateChambreForm(@PathVariable int id, Model model) {
        Chambre chambre = chambreService.findById(id);
        if (chambre != null) {
            model.addAttribute("chambre", chambre);
            return "updateChambre"; // Assumes there is an updateChambre.html Thymeleaf template
        } else {
            return "redirect:/chambre/all"; // Redirect if not found
        }
    }

    @PostMapping("/update/{id}")
    public String updateChambre(@PathVariable int id, @ModelAttribute Chambre chambre) {
        chambreService.updateChambre(id, chambre);
        return "redirect:/chambre/all"; // Redirect to the list of chambres after updating
    }

    @GetMapping("/delete/{id}")
    public String deleteChambre(@PathVariable int id) {
        chambreService.deleteChambre(id);
        return "redirect:/chambre/all"; // Redirect to the list of chambres after deleting
    }
}
