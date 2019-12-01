package fr.gtm.bovoyages.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.gtm.bovoyages.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
/*
	@Transactional
	@Modifying
	@Query(value = "Update clients c SET c.password=?1, c.digest=?2 WHERE c.id=?3", nativeQuery = true)
	void updateDigest(String password, String digest, long id);*/

    @Query(value = "SELECT digest from clients WHERE clients = ?1", nativeQuery = true)
    String getValues(String nom);

    Optional<Client> findByNom(String nom);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO clients (nom, digest) VALUES(?1, SHA2(?2,256))", nativeQuery = true)
    void createNewClient(String nom, String password);

    @Query(value = "Select * From clients c WHERE c.nom = ?1 and c.digest =?2", nativeQuery = true)
    Optional<Client> findByNomAndHashPassword(String nom, String password);

}
