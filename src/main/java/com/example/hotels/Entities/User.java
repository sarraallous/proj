package com.example.hotels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Setter
@Getter
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Reservation> reservation;

    public boolean getEtat() {
        return etat;
    }
}
