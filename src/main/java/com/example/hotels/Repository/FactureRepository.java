package com.example.hotels.Repository;

import com.example.hotels.Entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture,Integer> {

    public Facture findById(int id);
}
