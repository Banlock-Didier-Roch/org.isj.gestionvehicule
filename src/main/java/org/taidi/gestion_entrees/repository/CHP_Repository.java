package org.taidi.gestion_entrees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taidi.gestion_entrees.domaine.Command_has_produit;

import java.util.List;

public interface CHP_Repository extends JpaRepository<Command_has_produit, Long> {
    //Recupérer les produits et les quantités en fonction de l'ID de la commande
    @Query("select chp.produit, chp.quantite from Command_has_produit chp join chp.commande c where c.id = :id")
    List findAllByCommande(@Param("id") Long id);

    //Récupérer les produits et le nombre de fois qu'ils on été commandés
    @Query("select chp.produit, count(chp.produit) from Command_has_produit chp group by chp.produit order by count(chp.produit) DESC ")
    List findOrderCountPrduit();
    //List<Command_has_produit> findAllByIdCommand_id(@Param("id") Long id);
}
