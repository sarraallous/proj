package com.example.hotels.Services;

import com.example.hotels.Entities.Reservation;
import com.example.hotels.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    public List<Reservation> findAll()
    {
        return reservationRepository.findAll();
    }

    public Reservation findById(int id){
        Reservation reservation = reservationRepository.findById(id);
        return reservation;
    }

    public List<Reservation> findByUserUserId(int id) {
        return reservationRepository.findByUserUserId(id);
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void DeleteReservation(int id){
        reservationRepository.deleteById(id);
    }

    public Reservation updateReservation(int id, Reservation r){
        Reservation res = reservationRepository.findById(id);
        res.setReserve_id(id);
        res.setDateDebut(r.getDateDebut());
        res.setDateFin(r.getDateFin());
        res.setNum_adultes(r.getNum_adultes());
        res.setNum_enfants(r.getNum_enfants());
        res.setChambre(r.getChambre());
        reservationRepository.save(res);
        return res;
    }

    public void deleteReservation(int id) {
    }
}
