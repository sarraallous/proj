package com.example.hotels.Services;

import com.example.hotels.Entities.Chambre;
import com.example.hotels.Repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChambreService {
    @Autowired
    ChambreRepository chambreRepository;
    public List<Chambre> findAll()
    {
        return chambreRepository.findAll();
    }
    public Chambre findById(int id){
        Chambre facture = chambreRepository.findById(id);
        return facture;
    }
    public Chambre addChambre(Chambre c){
        Chambre co = chambreRepository.save(c);
        return c;
    }
    public void DeleteChambre(int id) {

        chambreRepository.deleteById(id);

    }
    public Chambre updateChambre(int id , Chambre e) {

        Chambre eq = chambreRepository.findById(id);
        eq.setId_room(id);
        eq.setDescription(e.getDescription());
        eq.setTyperoom(e.getTyperoom());
        eq.setDisponible(e.getDisponible());
        chambreRepository.save(eq);
        return eq;

    }
}
