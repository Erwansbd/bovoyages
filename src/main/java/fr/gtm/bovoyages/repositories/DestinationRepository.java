package fr.gtm.bovoyages.repositories;

import fr.gtm.bovoyages.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

}
