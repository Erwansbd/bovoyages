package fr.gtm.bovoyages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.gtm.bovoyages.entities.Voyageur;

/**
 *  @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 *  @version 1.0
 *  Repository de l'entite Voyageur.
 */
@Repository
public interface VoyageurRepository extends JpaRepository<Voyageur, Long> {

}
