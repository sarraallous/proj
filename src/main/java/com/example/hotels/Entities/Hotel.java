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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotel_id;
    private String nom;
    private String adresse;
    private String ville;
    private String image;
    @Enumerated(EnumType.STRING) // Specify that 'pays' is an Enum
    private Pays pays; // Pays is an enum
    private String description;
    @Enumerated(EnumType.STRING)
    private Etoiles numEtoiles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="hotel")
    private Set<Chambre> chambre;


}
