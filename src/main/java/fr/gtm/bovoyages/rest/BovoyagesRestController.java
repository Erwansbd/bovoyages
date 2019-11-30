package fr.gtm.bovoyages.rest;

import fr.gtm.bovoyages.dtos.DestinationDTO;
import fr.gtm.bovoyages.entities.Caddy;
import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.repositories.ClientRepository;
import fr.gtm.bovoyages.repositories.DatesVoyageRepository;
import fr.gtm.bovoyages.repositories.DestinationRepository;
import fr.gtm.bovoyages.repositories.VoyageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.inject.Inject;

@RestController
public class BovoyagesRestController {

	@Autowired
	DestinationRepository destinationRepository;
	@Autowired
	VoyageRepository voyageRepository;
	@Autowired
	DatesVoyageRepository datesVoyagesRepository;
	@Autowired
	ClientRepository clientRepository;
	
    //@Inject
	//Caddy caddy;

	// Digest digest;

	public static final Logger LOG = Logger.getLogger("Bovoyages");

	@GetMapping("/destination/all")
	public List<DestinationDTO> getAllDestinations() {
		List<Destination> destinationList = destinationRepository.findAll();
		List<DestinationDTO> destinationDTOList = new ArrayList<DestinationDTO>();
		for (Destination destination : destinationList) {
			destinationDTOList.add(new DestinationDTO(destination));
		}
		return destinationDTOList;
	}
	
	/*------front ---------------------------------------------------*/

	@GetMapping("/destination/{id}")
	public Destination getDestinationById(@PathVariable("id") long id) {
		Destination destination = destinationRepository.findById(id).get();
		return destination;
	}
	
	
	/*------front ---------------------------------------------------*/
	
	@GetMapping("/destination/byRegion")
	public List<Destination> getDestinationsByRegion(@RequestParam(name="region") String region) {
		List<Destination> destinations = destinationRepository.findDestinationByRegion(region);
		return destinations;
	}

//    @PostMapping("/destination/new")
//    public String createDestination(@RequestBody DestinationDTO destinationDTO) {
//        destinationRepository.save(destinationDTO.toDestination(destinationDTO));
//        return "Votre destination " +destinationDTO.getRegion()+ " avec comme description : " +destinationDTO.getDescription()+ " a bien été sauvegardé dans la base de données.";
//    }
	
	
	
	/*------front ?????------------*/

	@PostMapping("/destination/new")
	public String createDestination(@RequestBody Destination destination) {
		destinationRepository.save(destination);
		return "Votre destination " + destination.getRegion() + " avec comme description : "
				+ destination.getDescription() + " a bien été sauvegardé dans la base de données.";
	}
	
	
	/*------front -----------------------------------------------------*/

	@GetMapping("/destination/valid")
	public List<DestinationDTO> getDestinationNotDeleted() {
		List<DestinationDTO> destinationsdto = new ArrayList<DestinationDTO>();
		List<Destination> destinations = destinationRepository.getValidDestinations();
		for (Destination d : destinations) {
			destinationsdto.add(new DestinationDTO(d));
		}
		return destinationsdto;
	}
	
	/*------front ---------------------------------------------------*/

	@GetMapping("/destination/dates/valid/{id}")
	public List<DatesVoyage> getValidDatesVoyagesByDestinationId(@PathVariable("id") long id) {
		List<DatesVoyage> datesVoyages = new ArrayList<>();
		Destination destination = destinationRepository.getDestinationWithDatesById(id);
		for (DatesVoyage d : destination.getDatesVoyages()) {
			if (!d.isDeleted())
				datesVoyages.add(d);
		}
		return datesVoyages;
	}
	
	/*------front ---------------------------------------------------*/

	@PostMapping("voyage/new")
	public String createVoyage(@RequestBody Voyage voyage) {
		if (voyage.getDatesVoyage().getNbrePlaces() > voyage.getVoyageurs().size() && voyage.getVoyageurs().size() >= 1
				&& voyage.getVoyageurs().size() <= 10) 
		{
			LOG.info(" >>>>>" + voyage.getDatesVoyage().getNbrePlaces());
			voyage.getDatesVoyage().setNbrePlaces(voyage.getDatesVoyage().getNbrePlaces() - voyage.getVoyageurs().size());
			LOG.info(">>>>>" + voyage.getVoyageurs().size());
			LOG.info(" >>>>" + voyage.getDatesVoyage().getNbrePlaces());
			
			DatesVoyage dv = voyage.getDatesVoyage();
			if (dv.getNbrePlaces() == 0) {
				dv.setDeleted(true);
			}
			datesVoyagesRepository.save(dv);
			LOG.info(" >>>>" + voyage.getDatesVoyage().getNbrePlaces());
//			voyageRepository.updateDatesVoyageByVoyageId(voyage.getId());
		
//			List<Voyage> panier = caddy.getVoyages();
//			panier.add(voyage);
//			caddy.setVoyages(panier);
			return "Votre voyage " + voyage.getRegion() + "avec comme description " + voyage.getDescriptif()
					+ " a bien été crée.";
		} 
		
		else {
			return "Voyage non crée. Le nombre de voyageurs doit etre compris entre 1 et 9.";
			}
	}

	@GetMapping("/voyage/{id}")
	public Voyage getVoyageById(@PathVariable("id") long id) {
		Voyage voyage = voyageRepository.findById(id).get();
		return voyage;
	}

//	public void updateDV(DatesVoyage datesVoyage) {
//	DatesVoyage dv = datesVoyagesRepository.findById(datesVoyage.getId()).get();
//	dv.setDateAller(datesVoyage.getDateAller());
//	dv.setDateRetour(datesVoyage.getDateRetour());
//	dv.setDeleted(datesVoyage.isDeleted());
//	dv.setNbrePlaces(datesVoyage.getNbrePlaces());
//	datesVoyagesRepository.save(dv);

	// datesVoyagesRepository.save(datesVoyage);

//}

//	@GetMapping("/signing")
//	public String signing(Model model) {
//		Client client = new Client();
//		model.addAttribute("client", client);
//		return "signing";
//	}
	
	/*------front ---------------------------------------------------*/

	@PostMapping("/connexion")
	public boolean connexionTo(@RequestParam(name = "nom") String nom, @RequestParam(name = "password") String password)
			throws NoSuchAlgorithmException {
		boolean isAuth;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(password.getBytes());
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length() < 64) {
			hexString.insert(0, '0');
		}
		String pwdFin = hexString.toString();

		Optional<Client> opt = clientRepository.findByNomAndHashPassword(nom, pwdFin);

		if (opt.isPresent()) {
			isAuth = true;
			return isAuth;
		}
		isAuth = false;
		return isAuth;
	}
	
	/*------front ---------------------------------------------------*/

	@PostMapping("/signup")
	public boolean createClient(@RequestParam(name = "nom") String nom, @RequestParam(name = "password") String password)
			throws NoSuchAlgorithmException {
		boolean isAuth;
		Optional<Client> client = clientRepository.findByNom(nom);
		if (!client.isPresent()) {
			clientRepository.createNewClient(nom, password);
			isAuth=true;
			return isAuth;
		}
		 isAuth=false;
		return isAuth;

	}
	
	/*------front ?????------------*/

	@PostMapping("/signup/{nom}")
	public void createClient(@RequestParam(name = "nom") String nom, Model model) throws NoSuchAlgorithmException {
		System.out.println(nom);

	}
	
	/*------front ?????------------*/

	@GetMapping("/client/{id}")
	public Client getUserById(@PathVariable("id") long id) {
		Client client = clientRepository.findById(id).get();

		return client;
	}

}
