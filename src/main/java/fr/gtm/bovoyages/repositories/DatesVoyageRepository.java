package fr.gtm.bovoyages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.gtm.bovoyages.entities.DatesVoyage;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Repository de l'entite DatesVoyage.
 */
@Repository
public interface DatesVoyageRepository  extends JpaRepository <DatesVoyage, Long> {

}
