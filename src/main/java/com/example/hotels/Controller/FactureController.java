package com.example.hotels.Controller;

import com.example.hotels.Entities.Facture;
import com.example.hotels.Services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facture")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @GetMapping("/all")
    public List<Facture> getAllFactures() {
        return factureService.findAll();
    }

    @GetMapping("/{id}")
    public Facture getFactureById(@PathVariable int id) {
        return factureService.findById(id);
    }

    @PostMapping("/add")
    public Facture addFacture(@RequestBody Facture facture) {
        return factureService.addFacture(facture);
    }

    @PutMapping("/update/{id}")
    public Facture updateFacture(@PathVariable int id, @RequestBody Facture facture) {
        return factureService.updateFacture(id, facture);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFacture(@PathVariable int id) {
        factureService.DeleteFacture(id);
    }
}
