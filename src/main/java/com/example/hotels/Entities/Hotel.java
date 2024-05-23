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

    public String getImage() {
        if (this.image.startsWith("http") || this.image.startsWith("data:image")) {
            return this.image;
        } else if (!this.image.isEmpty()) {
            return this.image;
        } else {
            return "Default"; // return a default image if the image does not exist
        }
    }

    public Hotel() {
        this.image = "";
    }
}
