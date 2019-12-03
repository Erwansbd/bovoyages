package fr.gtm.bovoyages.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.gtm.bovoyages.entities.Client;




@SpringBootTest
class TestClientRepository {
	
	@Autowired
	ClientRepository clientRepository;

	@Test
	public void testFindByNom(){

	Optional<Client> client = clientRepository.findByNom("Jeremy");
	

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("Jeremy",client.get().getNom());
	}

	}
	
	
	@Test
	public void testCreateNewClient(){

	clientRepository.createNewClient("Alphonsefgfggf45fbvvc", "dfghjjk");
	Optional<Client> client = clientRepository.findByNom("Alphonsefgfggf45fbvvc");

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("Alphonsefgfggf45fbvvc",client.get().getNom());
	}

	}
	
	
	@Test
	public void testFindByNomAndHashPassword(){

	clientRepository.createNewClient("Alphonse4456fdfvffdfdbvb", "abc");
	Optional<Client> client = clientRepository.findByNomAndHashPassword("Alphonse4456fdfvffdfdbvb", "abc");

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("Alphonse4456fdfvffdfdbvb",client.get().getNom());
	}

	}
}
