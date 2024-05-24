package com.example.hotels.Repository;

import com.example.hotels.Entities.Chambre;
import com.example.hotels.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Integer> {

    List<Chambre> findAll();

    Optional<Chambre> findById(int id);

    List<Chambre> findByHotel(Hotel hotel); // Update method name to match the property name in Chambre entity
}
