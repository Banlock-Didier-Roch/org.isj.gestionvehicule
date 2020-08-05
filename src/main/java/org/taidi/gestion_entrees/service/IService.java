package org.taidi.gestion_entrees.service;


import org.taidi.gestion_entrees.domaine.Command_has_produit;
import org.taidi.gestion_entrees.domaine.Commande;
import org.taidi.gestion_entrees.domaine.Produit;

import java.util.List;


public interface IService {
    //Gestion commandes
    public Commande enregistrerCommande(Commande commande);
    public List<Commande> getCommandes();

    //Gestion produits
    public Produit enregistrerProduit(Produit produit);
    public List<Produit> getProduits(String service);

    //Gestion chp
    public Command_has_produit enregistrerCHP(Command_has_produit chp);

}
