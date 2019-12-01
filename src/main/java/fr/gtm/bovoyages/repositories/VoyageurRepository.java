package fr.gtm.bovoyages.repositories;

import fr.gtm.bovoyages.entities.Voyageur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyageurRepository extends JpaRepository<Voyageur, Long> {
}
