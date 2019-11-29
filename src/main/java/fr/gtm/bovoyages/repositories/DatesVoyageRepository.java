package fr.gtm.bovoyages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.gtm.bovoyages.entities.DatesVoyage;

@Repository
public interface DatesVoyageRepository  extends JpaRepository <DatesVoyage, Long> {

}
