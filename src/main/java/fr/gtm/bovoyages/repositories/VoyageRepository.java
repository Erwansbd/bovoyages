package fr.gtm.bovoyages.repositories;

import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.Voyage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Repository de l'entité Voyage.
 */
@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {
	
//	@Modifying
//	@Transactional
//	@Query("UPDATE Voyage v SET v.datesVoyage = ?1 WHERE v.id=?2")
//	public void updateDatesVoyageByVoyageId(long id);
	
	
	public Voyage findByClient(Client client);
	

}
