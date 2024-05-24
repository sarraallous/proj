package com.example.hotels.Controller;

import com.example.hotels.Entities.Reservation;
import com.example.hotels.Entities.User;
import com.example.hotels.Services.ReservationService;
import com.example.hotels.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public String getAllReservations(Model model) {
        List<Reservation> reservations = reservationService.findAll();
        model.addAttribute("reservations", reservations);
        return "reservation-list";
    }

    @GetMapping("/{id}")
    public String getReservationById(@PathVariable int id, Model model) {
        Reservation reservation = reservationService.findById(id);
        model.addAttribute("reservation", reservation);
        return "reservation-detail";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation"; // Assurez-vous que ce fichier existe
    }

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addReservation(@ModelAttribute Reservation reservation, RedirectAttributes redirectAttributes) {
        // Récupérez l'utilisateur associé à la réservation
        User user = reservation.getUser();

        // Maintenant que l'utilisateur est sauvegardé (ou qu'il existait déjà en base de données),
        // vous pouvez sauvegarder la réservation
        userService.save(user); // Assurez-vous que votre service d'utilisateur (UserService) a une méthode save()
        reservationService.addReservation(reservation);

        redirectAttributes.addFlashAttribute("successMessage", "Reservation added successfully!");
        return "redirect:/reservation/add";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReservation(@PathVariable int id, RedirectAttributes redirectAttributes) {
        reservationService.deleteReservation(id);
        redirectAttributes.addFlashAttribute("successMessage", "Reservation deleted successfully!");
        return "redirect:/reservation/all";
    }

    @PutMapping("/update/{id}")
    public String updateReservation(@PathVariable int id, @ModelAttribute Reservation reservation, RedirectAttributes redirectAttributes) {
        reservationService.updateReservation(id, reservation);
        redirectAttributes.addFlashAttribute("successMessage", "Reservation updated successfully!");
        return "redirect:/reservation/all";
    }
}
