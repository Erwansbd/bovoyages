package fr.gtm.bovoyages.repositories;

import fr.gtm.bovoyages.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {

}
