package com.example.hotels.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_room;
    private String description;
    private String image;
    @Enumerated(EnumType.STRING)
    private TypeRoom typeroom;
    private boolean disponible;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="chambre")
    private Set<Facture> facture;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reserv;
    @ManyToOne
    private Hotel hotel;

    public boolean getDisponible() {
        return disponible;
    }
    public Chambre(  int id_room,String description,
                     TypeRoom typeroom,
                     boolean disponible) {
        this.id_room=id_room;
        this.description=description;
        this.typeroom=typeroom;
        this.disponible=disponible;
    }
}

