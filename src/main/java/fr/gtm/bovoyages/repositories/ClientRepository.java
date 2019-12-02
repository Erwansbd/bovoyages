package fr.gtm.bovoyages.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.gtm.bovoyages.entities.Client;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Repository de l'entité Client.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
/*
	@Transactional
	@Modifying
	@Query(value = "Update clients c SET c.password=?1, c.digest=?2 WHERE c.id=?3", nativeQuery = true)
	void updateDigest(String password, String digest, long id);*/

	/**
	 * @param nom de type String.
	 * @return le mot de passe chiffré du client correspondant.
	 */
	@Query(value = "SELECT digest from clients WHERE clients = ?1", nativeQuery = true)
	String getValues(String nom);

	/**
	 * @param nom de type String
	 * @return un Client si il existe en BDD.
	 */
	public Optional<Client> findByNom(String nom);

	/**
	 * @param nom de type String.
	 * @param password de type String.
	 * Création d'un nouveau client.
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO clients (nom, digest) VALUES(?1, SHA2(?2,256))", nativeQuery = true)
	void createNewClient(String nom, String password);
	
	 /**
	 * @param nom de type String.
	 * @param password de type String.
	 * @return un Client si il existe en BDD.
	 */
	@Query(value ="Select * From clients c WHERE c.nom = ?1 and c.digest =?2", nativeQuery = true)
	    Optional<Client> findByNomAndHashPassword(String nom, String password);

}
