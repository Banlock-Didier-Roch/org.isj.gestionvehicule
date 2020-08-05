package org.taidi.gestion_entrees.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.taidi.gestion_entrees.domaine.Commande;
import org.taidi.gestion_entrees.domaine.Produit;
import org.taidi.gestion_entrees.service.IService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    IService service;

    @PostMapping("/Authentification")
    public String authentification(Model model, @RequestParam String identifiant, @RequestParam String password) {
        if(identifiant.equalsIgnoreCase("Caissier")&&password.equalsIgnoreCase("Caissier"))
            return "facturation_caissier";
        if(identifiant.equalsIgnoreCase("Admin")&&password.equalsIgnoreCase("Admin"))
            return "globales";
        model.addAttribute("message", "Login ou mot de passe oublié!!!");
        return "index";
    }

    @GetMapping("/globales")
    public String globales(Model model) {
        return "globales";
    }

    @GetMapping("/restaurant")
    public String restaurant(Model model) {
        return "restaurant";
    }

    @GetMapping("/location")
    public String location(Model model) {
        return "location";
    }

    @GetMapping("/traiteur")
    public String traiteur(Model model) {
        return "traiteur";
    }

    @GetMapping("/serigraphie")
    public String serigraphie(Model model) {
        return "serigraphie";
    }

    @GetMapping("/gest_personnel")
    public String gest_personnel(Model model) {
        return "gestion_personnel";
    }

    @GetMapping("/facturation")
    public String facturation(Model model) {
        List<Produit> produits = service.getProduits("Restauration");
        List<Produit> produitsPanier = new ArrayList<>();
        List<Integer> quantites = new ArrayList<>();
        double montantTotal = 0;

        //initialisation des listes de produits et aussi du panier des commandes(vide par défaut)
        model.addAttribute("produitsRestauration", produits);
        model.addAttribute("produitsPanier", produitsPanier);
        model.addAttribute("quantites", quantites);

        return "facturation";
    }

    @GetMapping("/facturation_caissier")
    public String facturation_caissier(Model model) {
        return "facturation_caissier";
    }

    @GetMapping("/recap_ventes")
    public String recap_ventes(Model model) {
        return "recapitulatif_ventes";
    }

    @GetMapping("/facturation2")
    public @ResponseBody
    String EnregistrerCommande(@RequestParam("name") String name) {
        // your logic here
        return name;
    }
}
