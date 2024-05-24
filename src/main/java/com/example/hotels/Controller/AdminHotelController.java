package com.example.hotels.Controller;

import com.example.hotels.Entities.Chambre;
import com.example.hotels.Entities.Hotel;
import com.example.hotels.Services.hotelservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{hotelId}/rooms")
    public String getHotelRooms(@PathVariable("hotelId") int hotelId, Model model) {
        Hotel hotel = hotelService.findById(hotelId); // Fetch the Hotel object by ID
        if (hotel != null) {
            List<Chambre> rooms = hotelService.findRoomsByHotelId(hotel);
            model.addAttribute("rooms", rooms);
            model.addAttribute("hotel", hotel); // Add the hotel object to the model
            // Log the retrieved hotel object
            System.out.println("Retrieved hotel: " + hotel);
            return "admin/rooms";
        } else {
            // Handle case where hotel is not found for the given ID
            // You can redirect to an error page or display an appropriate message
            return "redirect:/admin/hotels/retrieve-all-hotel"; // Redirect to hotel list
        }
    }


    @GetMapping("/{hotelId}/rooms/add")
    public String showAddRoomForm(@PathVariable("hotelId") int hotelId, Model model) {
        Hotel hotel = hotelService.findById(hotelId); // Fetch the Hotel object by ID
        if (hotel != null) {
            model.addAttribute("chambre", new Chambre());
            model.addAttribute("hotel", hotel); // Add the hotel object to the model
            return "admin/addroomform";
        } else {
            // Handle case where hotel is not found for the given ID
            // You can redirect to an error page or display an appropriate message
            return "redirect:/admin/hotels/retrieve-all-hotel"; // Redirect to hotel list
        }
    }

    @PostMapping("/{hotelId}/rooms/add")
    public String addRoom(@PathVariable("hotelId") int hotelId, @ModelAttribute Chambre chambre) {
        hotelService.addRoomToHotel(hotelId, chambre);
        return "redirect:/admin/hotels/{hotelId}/rooms";
    }

    @GetMapping("/{hotelId}/rooms/update/{roomId}")
    public String showUpdateRoomForm(@PathVariable("hotelId") int hotelId, @PathVariable("roomId") int roomId, Model model) {
        Optional<Chambre> chambre = Optional.ofNullable(hotelService.findRoomById(roomId));
        model.addAttribute("chambre", chambre.orElse(null));
        return "admin/updateroom";
    }

    @PostMapping("/{hotelId}/rooms/update/{roomId}")
    public String updateRoom(@PathVariable("hotelId") int hotelId, @PathVariable("roomId") int roomId, @ModelAttribute Chambre chambre) {
        hotelService.updateRoom(roomId, chambre);
        return "redirect:/admin/hotels/{hotelId}/rooms";
    }

    @GetMapping("/{hotelId}/rooms/delete/{roomId}")
    public String deleteRoom(@PathVariable("hotelId") int hotelId, @PathVariable("roomId") int roomId) {
        hotelService.deleteRoom(roomId);
        return "redirect:/admin/hotels/{hotelId}/rooms";
    }
}
