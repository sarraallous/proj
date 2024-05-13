package com.example.hotels.Services;

import com.example.hotels.Entities.Chambre;
import com.example.hotels.Entities.Facture;
import com.example.hotels.Repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {
    @Autowired
    FactureRepository factureRepository;
    public List<Facture> findAll()
    {
        return factureRepository.findAll();
    }
    public Facture findById(int id){
        Facture facture = factureRepository.findById(id);
        return facture;
    }
    public Facture addFacture(Facture c){
        Facture co = factureRepository.save(c);
        return c;
    }
    public void DeleteFacture(int id) {

        factureRepository.deleteById(id);

    }
    public Facture updateFacture(int id , Facture e) {

        Facture eq = factureRepository.findById(id);
        eq.setFacture_id(id);
        eq.setMontant_total(e.getMontant_total());
        eq.setEtat_paiement(e.getEtat_paiement());
        factureRepository.save(eq);
        return eq;

    }
}
