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

	Optional<Client> client = clientRepository.findByNom("AlphonseDansletas");
	

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("AlphonseDansletas",client.get().getNom());
	}

	}
	
	
	@Test
	public void testCreateNewClient(){

	clientRepository.createNewClient("user0", "dfghjjk");
	Optional<Client> client = clientRepository.findByNom("user0");

	if (client.isPresent()) {
	assertNotNull(client);
	assertEquals("user0",client.get().getNom());
	}
	
	clientRepository.delete(client.get());

	}
	
	
	
	@Test
	public void testFindByNomAndHashPassword(){

	clientRepository.createNewClient("user1", "abc");
	Optional<Client> client2 = clientRepository.findByNomAndHashPassword("user1", "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");

	if (client2.isPresent()) {
	assertNotNull(client2);
	assertEquals("user1",client2.get().getNom());
	}
	
	clientRepository.delete(client2.get());
	

	}



	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
