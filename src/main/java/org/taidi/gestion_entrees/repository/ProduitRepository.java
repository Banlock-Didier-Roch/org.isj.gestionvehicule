package org.taidi.gestion_entrees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taidi.gestion_entrees.domaine.Commande;
import org.taidi.gestion_entrees.domaine.Produit;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Query("select p from Produit p where p.utilisateur.id =:id")
    List<Produit> findAllByUser(@Param("id") Long id);

    List<Produit> findAllByService(String service);

}
