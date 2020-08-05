package org.taidi.gestion_entrees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taidi.gestion_entrees.domaine.Commande;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("select c from Commande c where c.utilisateur.id =:id")
    List<Commande> findAllByUser(@Param("id") Long id);

}
