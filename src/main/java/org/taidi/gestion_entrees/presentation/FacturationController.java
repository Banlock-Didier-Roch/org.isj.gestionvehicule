package org.taidi.gestion_entrees.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.taidi.gestion_entrees.domaine.Command_has_produit;
import org.taidi.gestion_entrees.domaine.Commande;
import org.taidi.gestion_entrees.domaine.Produit;
import org.taidi.gestion_entrees.domaine.ProduitWrapper;
import org.taidi.gestion_entrees.service.IService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class FacturationController {

    @Autowired
    IService service;

    @RequestMapping(value = "/EnregistrerCommande", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String Submit(@RequestBody ProduitWrapper commande) {

        System.out.println("\n\n"+commande.getProduits().get(0).getDesignation()+" "+commande.getQuantites()[0]+" "+commande.getPrixTotal()+"\n\n");

        Command_has_produit chp;
        Commande commande1 = new Commande();

        //Enregistrement de la commande
        commande1.setDate(new SimpleDateFormat("dd-MM-YYYY").format(new Date()));
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        commande1.setHeure(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()));
        commande1.setPrix_total(commande.getPrixTotal());
        commande1 = service.enregistrerCommande(commande1);

        //remplisage CHP
        for (int i=0; i<commande.getProduits().size();i++) {

            //Initialisation chp
            chp = new Command_has_produit();
            chp.setCommande(commande1);
            chp.setProduit(commande.getProduits().get(i));
            chp.setQuantite(commande.getQuantites()[i]);

            //Enregistrement chp
            service.enregistrerCHP(chp);
        }

        return "TestReussi";
    }




}
