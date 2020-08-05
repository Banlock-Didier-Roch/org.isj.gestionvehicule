package org.taidi.gestion_entrees.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String date;

    private String heure;
    private double prix_total;
    private double montant_verse;
    private double montant_rembourse;

    @ManyToOne
    private Utilisateur utilisateur;


}
