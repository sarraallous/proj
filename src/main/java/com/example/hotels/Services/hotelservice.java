package com.example.hotels.Services;

import com.example.hotels.Entities.Hotel;
import com.example.hotels.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class hotelservice {
    @Autowired
    HotelRepository hotelRepository;
    public List<Hotel> findAll()
    {
        return hotelRepository.findAll();
    }

    public Hotel findById(int id)
    {
       Hotel hotel = hotelRepository.findById(id);
        return hotel;
    }

    public Hotel addHotel(Hotel ee) {
        Hotel co = hotelRepository.save(ee);
        return ee;
    }
    public void DeleteHotel(int id) {

        hotelRepository.deleteById(id);

    }

    public Hotel updateHotel(int id , Hotel e) {

        Hotel ht = hotelRepository.findById(id);
        ht.setHotel_id(id);
        ht.setNom(e.getNom());
        ht.setAdresse(e.getAdresse());
        ht.setPays(e.getPays());
        ht.setVille(e.getVille());
        ht.setDescription(e.getDescription());
        ht.setNumEtoiles(e.getNumEtoiles());
        ht.setChambre(e.getChambre());
        hotelRepository.save(ht);
        return ht;

    }
}

