package fr.gtm.bovoyages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.gtm.bovoyages.entities.Voyageur;

@Repository
public interface VoyageurRepository extends JpaRepository<Voyageur, Long> {

}
