package fr.gtm.bovoyages.rest;

import fr.gtm.bovoyages.dtos.DestinationDTO;
import fr.gtm.bovoyages.entities.Caddy;
import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.entities.Voyageur;
import fr.gtm.bovoyages.repositories.ClientRepository;
import fr.gtm.bovoyages.repositories.DatesVoyageRepository;
import fr.gtm.bovoyages.repositories.DestinationRepository;
import fr.gtm.bovoyages.repositories.VoyageRepository;
import fr.gtm.bovoyages.repositories.VoyageurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 *  @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 *  @version 1.0 
 *  Controller de type REST de BoVoyages.
 */
@RestController
public class BovoyagesRestController {

	/**
	 * Attribut destinationRepository de type DestinationRepository.
	 */
	@Autowired
	DestinationRepository destinationRepository;
	/**
	 * Attribut voyageRepository de type VoyageRepository.
	 */
	@Autowired
	VoyageRepository voyageRepository;
	/**
	 * Attribut datesVoyagesRepository de type DatesVoyageRepository.
	 */
	@Autowired
	DatesVoyageRepository datesVoyagesRepository;
	/**
	 * Attribut clientRepository de type ClientRepository.
	 */
	@Autowired
	ClientRepository clientRepository;
	/**
	 * Attribut voyageurRepository de type VoyageurRepository.
	 */
	@Autowired
	VoyageurRepository voyageurRepository;
	/**
	 * Attribut javamailsender de type JavaMailSender.
	 */
	@Autowired 
	JavaMailSender javamailsender;
	
    //@Inject
	//Caddy caddy;

	// Digest digest;

	/**
	 * Attribut static final LOG de type Logger.
	 */
	public static final Logger LOG = Logger.getLogger("Bovoyages");

	/**
	 * @return la liste de toutes les DestinationDTO présentes en BDD.
	 */
	@GetMapping("/destinationDTO/all")
	public List<DestinationDTO> getAllDestinationsDTO() {
		List<Destination> destinationList = destinationRepository.findAll();
		List<DestinationDTO> destinationDTOList = new ArrayList<DestinationDTO>();
		for (Destination destination : destinationList) {
			destinationDTOList.add(new DestinationDTO(destination));
		}
		return destinationDTOList;
	}
	
	/**
	 * @return la liste de toutes les destinations présentes en BDD.
	 */
	@GetMapping("/destination/all")
	public List<Destination> getAllDestinations() {
		List<Destination> destinationList = destinationRepository.findAll();
		return destinationList;
	}
	
	/*------front ---------------------------------------------------*/

	/**
	 * @param id de type long
	 * @return une destination de type destination correspondant à l'id en argument de la méthode.
	 */
	@GetMapping("/destination/{id}")
	public Destination getDestinationById(@PathVariable("id") long id) {
		Destination destination = destinationRepository.findById(id).get();
		if(destination.isRaye()) return null;
		return destination;
	}
	
	
	/*------front ---------------------------------------------------*/
	
	/**
	 * @param region de type String
	 * @return la liste de toutes les destinations correspondante à la recherche par region associee.
	 */
	@GetMapping("/destination/byRegion")
	public List<Destination> getDestinationsByRegion(@RequestParam(name="region") String region) {
		List<Destination> destinations = destinationRepository.findDestinationByRegion(region);
		List<Destination> destinationsFinal = new ArrayList<>();
		for(Destination d: destinations) {
			if(!d.isRaye()) destinationsFinal.add(d);
		}
		return destinationsFinal;
	}

//    @PostMapping("/destination/new")
//    public String createDestination(@RequestBody DestinationDTO destinationDTO) {
//        destinationRepository.save(destinationDTO.toDestination(destinationDTO));
//        return "Votre destination " +destinationDTO.getRegion()+ " avec comme description : " +destinationDTO.getDescription()+ " a bien été sauvegardé dans la base de données.";
//    }
	
	
	
	/*------front ?????------------*/

	/**
	 * @param destination de type Destination
	 * @return une confirmation de type String de la création d'une nouvelle destination.
	 */
	@PostMapping("/destination/new")
	public String createDestination(@RequestBody Destination destination) {
		destination.setRaye(false);
		destinationRepository.save(destination);
		return "Votre destination " + destination.getRegion() + " avec comme description : "
				+ destination.getDescription() + " a bien été sauvegardé dans la base de données.";
	}
	
	
	/*------front ---------------------------------------------------*/

	/**
	 * @return la liste de toutes les DestinationDTO valides (non rayees).
	 */
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

	/**
	 * @param id de type long.
	 * @return une liste de DatesVoyage de toutes les dates valides pour une destination voulue.
	 */
	@GetMapping("/destination/dates/valid/{id}")
	public List<DatesVoyage> getValidDatesVoyagesByDestinationId(@PathVariable("id") long id) {
		List<DatesVoyage> datesVoyages = new ArrayList<>();
		Destination destination = destinationRepository.getDestinationWithDatesById(id);
		if(destination.isRaye())return null;
		for (DatesVoyage d : destination.getDatesVoyages()) {
			if (!d.isDeleted())
				datesVoyages.add(d);
		}
		return datesVoyages;
	}
	
	/*------front ---------------------------------------------------*/

	/**
	 * @param voyage de type Voyage.
	 * @return une confirmation de type String suite à la persistence d'un nouveau voyage en BDD.
	 */
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
			voyageRepository.save(voyage);
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
	
	
	
	

	/**
	 * @param id de type long.
	 * @return un voyage de type Voyage par son id.
	 */
	@GetMapping("/voyage/{id}")
	public Voyage getVoyageById(@PathVariable("id") long id) {
		Voyage voyage = voyageRepository.findById(id).get();
		return voyage;
	}
	
	/**
	 * @return l'ensemble des voyages présent en BDD.
	 */
	@GetMapping("/voyage/all")
	public List<Voyage> getAllVoyages() {
		return voyageRepository.findAll();
	}
	
	
	/**
	 * @param id de type long.
	 * @return la liste de l'ensemble des voyages commandes par un client.
	 */
	@GetMapping("/voyage/client/{id}")
	public List<Voyage> getVoyagesByClientId(@PathVariable("id") long id) {
		List<Voyage> voyages = getAllVoyages();
		List<Voyage> voyagesDuClient = new ArrayList<>();
		for(Voyage v : voyages) {
			Client client = v.getClient();
			if (client.getId()==id) voyagesDuClient.add(v);
		}
		return voyagesDuClient;
	}
	
	/**
	 * @param id de type long.
	 * @return l'ensemble des voyages non payes par le client en question.
	 */
	@GetMapping("/voyage/client/nonpaye/{id}")
	public List<Voyage> getVoyagesNotPaidByClientId(@PathVariable("id") long id) {
		List<Voyage> voyages = voyageRepository.findAll();
		List<Voyage> voyagesDuClient = new ArrayList<>();
		for(Voyage v : voyages) {
			Client client = v.getClient();
			if (client.getId()==id && !v.isPaye()) voyagesDuClient.add(v);
		}
		return voyagesDuClient;
	}
	
	
	/**
	 * @param id de type long.
	 * @return une confirmation de type String suite à la suppression du voyage en BDD.
	 */
	@PostMapping("/voyage/delete/{id}") 
	public String deleteVoyageById(@PathVariable("id") long id) {
		voyageRepository.deleteById(id);
		return "Le voyage a bien été supprimé.";
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

	/**
	 * @param nom de type String.
	 * @param password de type String.
	 * @return is Auth de type boolean.
	 * @throws NoSuchAlgorithmException.
	 */
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

	/**
	 * @param nom de type String.
	 * @param password de type String.
	 * @return isAuth de type boolean.
	 * @throws NoSuchAlgorithmException
	 */
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

	/**
	 * @param id de type long.
	 * @return client de type Client correspondant à l'id en argument de la methode.
	 */
	@GetMapping("/client/{id}")
	public Client getUserById(@PathVariable("id") long id) {
		Client client = clientRepository.findById(id).get();

		return client;
	}
	
	/**
	 * @param DatesVoyageId de type long.
	 * @return une destination de type Destination correspondant à la date de voyage selectionnee.
	 */
	@GetMapping("/dates/destination/{id}")
	public Destination getDestinationByDatesVoyageId(@PathVariable("id")long DatesVoyageId) {
		List<Destination> destinations = destinationRepository.getValidDestinations();
		for (Destination d : destinations) {
	     List<DatesVoyage> datesvoyages = getValidDatesVoyagesByDestinationId(d.getId());
		for( DatesVoyage dv : datesvoyages) {
			if(dv.getId()==(DatesVoyageId)) {
				return d;
			}
		}	
	}
		return null;}
	
	
	/**
	 * @param id de type String.
	 * @return une confirmation de validation de panier.
	 */
	@GetMapping("/caddy/confirm/{id}")
	public String ConfirmCaddy(@PathVariable("id") long id) {
		List<Voyage> voyages = voyageRepository.findAll();
		List<Voyage> voyagesDuClient = new ArrayList<>();
		for(Voyage v : voyages) {
			Client client = v.getClient();
			if (client.getId()==id && !v.isPaye()) voyagesDuClient.add(v);
		}
//		Optional<Client> clientMail = clientRepository.findById(id);
		double prixTotal=0;
		for(Voyage v : voyagesDuClient) {
			prixTotal+= v.getDatesVoyage().getPrixHT()*v.getVoyageurs().size();
		}
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(voyagesDuClient.get(0).getClient().getEmail());
			mailMessage.setFrom("service-achats@bovoyages.fr");
			mailMessage.setSubject("Confirmation commande");
			String message ="Cher "+voyagesDuClient.get(0).getClient().getNom()+","+"\n"+
					"Voici le recapitulatif de votre commande:";
			for(Voyage v: voyagesDuClient) {
				message+="\n"+v.toString();
				message+="\n"+"Nombre de participant(s) : "+v.getVoyageurs().size() +" Montant : "+v.getDatesVoyage().getPrixHT()*v.getVoyageurs().size()+" euros.";
//				v.setPaye(true);
				}
			message+="\n"+"\n"+"Vous allez etre debite dans les prochains jours de "+prixTotal+" euros."+"\n"+"\n"
			+"Nous vous remercions infiniment. "+"\n"+"A tres bientot sur notre site."+"\n"+"\n"
			+"service-achat Bovoyages.";
			mailMessage.setText(message);
			mailMessage.setSentDate(new Date(System.currentTimeMillis()));
			javamailsender.send(mailMessage);
		String messageConfirmation ="La commande a bien été confirmée. Vous allez etre débité dans les prochains jours de "+prixTotal+"€"+"."+
		"Un mail de confirmation vous a été envoyé à l'adresse suivante : "+ voyagesDuClient.get(0).getClient().getEmail()+".";
		return messageConfirmation;
	}
	
	/**
	 * @param voyageur de type Voyageur.
	 * @return une confirmation de type String comme quoi le voyageur a bien ete mis à jour.
	 */
	@PostMapping("/voyage/voyageur")
	public String updateVoyageur(@RequestBody Voyageur voyageur) {
		Voyageur voyageurMisAJour = new Voyageur(voyageur.getId(), voyageur.getCivilite(), voyageur.getNom(), voyageur.getPrenom(), voyageur.getDateNaissance());
		voyageurRepository.save(voyageurMisAJour);
		return "Voyageur : "+voyageur.getNom()+" "+voyageur.getPrenom() +" mis à jour.";
	}

}
