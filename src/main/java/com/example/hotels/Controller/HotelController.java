package com.example.hotels.Controller;

import com.example.hotels.Entities.Hotel;
import com.example.hotels.Entities.Pays;
import com.example.hotels.Services.hotelservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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

    @PostMapping("/add")
    public String addHotel(@ModelAttribute Hotel hotel, @RequestParam("image") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            // Define the path where you want to save the images
            Path path = Paths.get("src/main/resources/static/images/" + file.getOriginalFilename());
            file.transferTo(path);
    
            // Save the image name to the database
            hotel.setImage(file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        hotelService.addHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage", "Hotel added successfully!");
        return "redirect:/hotels/retrieve-all-hotel";
    }


    
    @GetMapping("/filter")
    public String filterHotelsBypays(@RequestParam("pays") String pays, Model model) {
        List<Hotel> listHotels;
        if ("all".equals(pays)) {
            listHotels = hotelService.findAll();
            pays = null; // set pays to null if "all" is selected
        } else {
            listHotels = hotelService.findBypays(pays);
        }
        model.addAttribute("allPays", Arrays.asList(Pays.values()));
        model.addAttribute("hotels", listHotels);
        model.addAttribute("selectedPays", pays); // add selectedPays to the model
        return "hotels";
    }

@ModelAttribute("allPays")
public List<Pays> populatePays() {
    return Arrays.asList(Pays.values());
}
/* 

    @PostMapping("/add") // Changed the mapping to /add
    public String addHotel(@ModelAttribute Hotel hotel, RedirectAttributes redirectAttributes) {
        hotelService.addHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage", "Hotel added successfully!");
        return "redirect:/hotels/retrieve-all-hotel"; // Adjusted the redirect URL
    }
*/
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
