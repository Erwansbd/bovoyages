package fr.gtm.bovoyages.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	clientRepository.createNewClient("Alphonse45f", "dfghjjk");
	Optional<Client> client = clientRepository.findByNom("Alphonse45f");

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("Alphonse45f",client.get().getNom());
	}

	}
	
	
	@Test
	public void testFindByNomAndHashPassword(){

	clientRepository.createNewClient("Alphonse4456", "abc");
	Optional<Client> client = clientRepository.findByNomAndHashPassword("Alphonse4456", "abc");

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("Alphonse4456",client.get().getNom());
	}

	}



	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
