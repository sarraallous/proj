package com.example.hotels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facture_id;
    private float montant_total;
    private boolean etat_paiement;
    @ManyToOne
    private Chambre chambre;

    public boolean getEtat_paiement() {
        return etat_paiement;
    }
}
