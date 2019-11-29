package fr.gtm.bovoyages.repositories;

import fr.gtm.bovoyages.entities.Destination;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

	
	@Query("SELECT d FROM Destination d WHERE d.raye =0")
	List<Destination> getValidDestinations();
	
	@Query("SELECT d FROM Destination d JOIN FETCH d.datesVoyages WHERE d.id=?1 ")
	Destination getDestinationWithDatesById(long id);

}
