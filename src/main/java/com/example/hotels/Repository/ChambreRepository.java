package com.example.hotels.Repository;

import com.example.hotels.Entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Integer> {

    public List<Chambre> findAll();
    public Chambre findById(int id);
}
