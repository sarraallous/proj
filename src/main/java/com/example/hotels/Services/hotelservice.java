package com.example.hotels.Services;

import com.example.hotels.Entities.Chambre;
import com.example.hotels.Entities.Hotel;
import com.example.hotels.Entities.Pays;
import com.example.hotels.Repository.ChambreRepository;
import com.example.hotels.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class hotelservice {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ChambreRepository chambreRepository;

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
        return hotelRepository.findById(id).orElse(null);
    }

    public List<Chambre> findRoomsByHotelId(Hotel hotel) {
        return chambreRepository.findByHotel(hotel);
    }

    public Chambre findRoomById(int roomId) {
        return chambreRepository.findById(roomId).orElse(null);
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

    public List<Hotel> findBypays(String pays) {
        String capitalizedPays = pays.substring(0, 1).toUpperCase() + pays.substring(1).toLowerCase();
        Pays paysEnum = Pays.valueOf(capitalizedPays);
        return hotelRepository.findByPays(paysEnum);
    }

    public void addRoomToHotel(int hotelId, Chambre chambre) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            chambre.setHotel(hotel);
            chambreRepository.save(chambre);
        } else {
            throw new RuntimeException("Hotel not found with ID: " + hotelId);
        }
    }

    public void updateRoom(int roomId, Chambre chambre) {
        Optional<Chambre> existingChambre = chambreRepository.findById(roomId);
        if (existingChambre.isPresent()) {
            Chambre chambreToUpdate = existingChambre.get();
            chambreToUpdate.setDescription(chambre.getDescription());
            chambreToUpdate.setDisponible(chambre.getDisponible());
            // Update other fields as needed
            chambreRepository.save(chambreToUpdate);
        } else {
            throw new RuntimeException("Room not found with ID: " + roomId);
        }
    }

    public void deleteRoom(int roomId) {
        chambreRepository.deleteById(roomId);
    }
}
