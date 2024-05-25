package com.example.hotels.Services;

import com.example.hotels.Entities.Chambre;
import com.example.hotels.Entities.Hotel;
import com.example.hotels.Repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreService {
    @Autowired
    private ChambreRepository chambreRepository;

    public List<Chambre> findAll() {
        return chambreRepository.findAll();
    }

    public Chambre findById(int id) {
        Optional<Chambre> chambre = chambreRepository.findById(id);
        return chambre.orElse(null); // or throw an exception if not found
    }

    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    public void deleteChambre(int id) {
        chambreRepository.deleteById(id);
    }

    public Chambre updateChambre(int id, Chambre e) {
        Optional<Chambre> optionalChambre = chambreRepository.findById(id);
        if (optionalChambre.isPresent()) {
            Chambre eq = optionalChambre.get();
            eq.setId_room(id);
            eq.setDescription(e.getDescription());
            eq.setTyperoom(e.getTyperoom());
            eq.setDisponible(e.getDisponible());
            return chambreRepository.save(eq);
        } else {
            throw new RuntimeException("Chambre not found with ID: " + id);
        }
    }

    public List<Chambre> findRoomsByHotel(Hotel hotel) {
        return chambreRepository.findByHotel(hotel);
    }

}
