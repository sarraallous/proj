package com.example.hotels.Repository;

import com.example.hotels.Entities.Hotel;
import com.example.hotels.Entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

  List<Hotel> findAll();

  Optional<Hotel> findById(int id);

  List<Hotel> findByPays(Pays pays);
}
