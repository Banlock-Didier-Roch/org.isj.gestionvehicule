package org.taidi.gestion_entrees.presentation.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.taidi.gestion_entrees.domaine.Command_has_produit;
import org.taidi.gestion_entrees.domaine.Commande;
import org.taidi.gestion_entrees.domaine.Produit;
import org.taidi.gestion_entrees.domaine.ProduitWrapper;
import org.taidi.gestion_entrees.repository.CHP_Repository;
import org.taidi.gestion_entrees.repository.CommandeRepository;
import org.taidi.gestion_entrees.repository.ProduitRepository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gestionTAIDI/api")
public class TestApi {
    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CHP_Repository chp_repository;

    @RequestMapping(value = "/commandes/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commande> listeCommandeApi(@PathVariable Long id) {
        return commandeRepository.findAllByUser(id);
    }

    @RequestMapping(value = "/chp_api", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String listeProduitsCommandesApi(@RequestParam Long idC ) {
        List object = chp_repository.findAllByCommande(idC);
        Object[] objects=(Object[])object.get(0);
        Produit produit = (Produit) objects[0];
        return produit.getDesignation()+objects[1];
    }

    @RequestMapping(value = "/chp_api2", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List listeProduitsOccurencesApi() {
        return  chp_repository.findOrderCountPrduit();
    }

    @RequestMapping(value = "/EnregistrerCommande", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String Submit(@RequestBody ProduitWrapper commande) {

        System.out.println("\n\n"+commande.getProduits().get(0).getDesignation()+" "+commande.getQuantites()[0]+" "+commande.getPrixTotal()+"\n\n");

        return "TestReussi";
    }

}
