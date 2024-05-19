package com.example.hotels.Services;

import com.example.hotels.Entities.Hotel;
import com.example.hotels.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class hotelservice {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void deleteHotel(int id) {
        hotelRepository.deleteById(id);
    }

    public Hotel findById(int id) {
        return hotelRepository.findById(id);
    }

    public void updateHotel(int id, Hotel hotel) {
        Hotel existingHotel = findById(id);
        if (existingHotel != null) {
            existingHotel.setNom(hotel.getNom());
            existingHotel.setAdresse(hotel.getAdresse());
            existingHotel.setDescription(hotel.getDescription());
            hotelRepository.save(existingHotel);
        }
    }



}
