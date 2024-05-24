package com.example.hotels.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

    private int num_adultes;

    private int num_enfants;
    @ManyToOne
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Chambre> chambre;
}

