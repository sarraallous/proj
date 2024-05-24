package com.example.hotels.Controller;

import com.example.hotels.Entities.Chambre;
import com.example.hotels.Entities.Hotel;
import com.example.hotels.Repository.ChambreRepository;
import com.example.hotels.Services.ChambreService;
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
    private ChambreRepository chambreRepository;

    @GetMapping("/all")
    public String getChambre(Model model) {
        List<Chambre> listChambre = chambreService.findAll();
        model.addAttribute("listChambre", listChambre);
        return "rooms";
    }



    @GetMapping("/{id}")
    public Chambre getChambreById(@PathVariable int id) {
        return chambreService.findById(id);
    }

    @PostMapping("/add")
    public Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteChambre(@PathVariable int id) {
        chambreService.DeleteChambre(id);
    }

    @PutMapping("/update/{id}")
    public Chambre updateChambre(@PathVariable int id, @RequestBody Chambre chambre) {
        return chambreService.updateChambre(id, chambre);
    }
}
