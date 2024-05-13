package com.example.hotels.Repository;

import com.example.hotels.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    //public List<Hotel> findAll();
    //public Hotel addHotel(Hotel ee);
    //public void DeleteHotel(int id);

  //  public Hotel updateHotel(Long id , Hotel e);

    public Hotel findById(int id);
}
