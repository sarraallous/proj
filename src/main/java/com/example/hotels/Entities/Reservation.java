package com.example.hotels.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reserve_id;
    private Date dateDebut;
    private Date dateFin;

    private int num_adultes;

    private int num_enfants;
    @ManyToOne
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Chambre> chambre;
}

