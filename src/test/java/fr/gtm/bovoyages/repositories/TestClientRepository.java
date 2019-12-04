package fr.gtm.bovoyages.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	clientRepository.createNewClient("Alphonsefgfggdsf45fbvvc", "dfghjjk");
	Optional<Client> client = clientRepository.findByNom("Alphonsefgfggdsf45fbvvc");

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("Alphonsefdfdgfggdsf45fbvvc",client.get().getNom());
	}

	}
	
	
	@Test
	public void testFindByNomAndHashPassword(){

	clientRepository.createNewClient("Alphonse4456ffffdfvffdfdbvb", "abc");
	Optional<Client> client = clientRepository.findByNomAndHashPassword("Alphonse4456ffffdfvffdfdbvb", "abc");

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("Alphonse4456ffffdfvffdfdbvb",client.get().getNom());
	}

	}



	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
