package com.example.hotels.Repository;

import com.example.hotels.Entities.Hotel;
import com.example.hotels.Entities.Pays;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    //public List<Hotel> findAll();
    //public Hotel addHotel(Hotel ee);
    //public void DeleteHotel(int id);

  //  public Hotel updateHotel(Long id , Hotel e);
  List<Hotel> findByPays(Pays pays);

    public Hotel findById(int id);
}

