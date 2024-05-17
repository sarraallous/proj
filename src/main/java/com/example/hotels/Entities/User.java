package com.example.hotels.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String nom;
    private String prenom;
    private String mdp;
    private String email;
    private String numTel;
    private boolean etat;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Reservation> reservation;

    public User() {
    }

    public User(int userId, String nom, String mdp) {
        this.userId = userId;
        this.nom = nom;
        this.mdp = mdp;
    }
}
