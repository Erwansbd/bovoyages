package fr.gtm.bovoyages.rest;

import fr.gtm.bovoyages.dtos.DestinationDTO;
import fr.gtm.bovoyages.entities.*;
import fr.gtm.bovoyages.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.MulticastSocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BovoyagesRestController {

    @Autowired
    DestinationRepository destinationRepository;
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    DatesVoyageRepository datesVoyageRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    VoyageurRepository voyageurRepository;

    @GetMapping("/destinationDTO/all")
    public List<DestinationDTO> getAllDestinationsDTO() {
        List<Destination> destinationList = destinationRepository.findAll();
        List<DestinationDTO> destinationDTOList = new ArrayList<DestinationDTO>();
        for (Destination destination : destinationList) {
            destinationDTOList.add(new DestinationDTO(destination));
        }
        return destinationDTOList;
    }

    @GetMapping("/destination/all")
    public List<Destination> getAllDestinations() {
        List<Destination> destinationList = destinationRepository.findAll();
        return destinationList;
    }

    @GetMapping("/destination/{id}")
    public Destination getDestinationById(@PathVariable("id") long id) {
        Destination destination = destinationRepository.findById(id).get();
        return destination;
    }

    @GetMapping("/destination/byRegion")
    public List<Destination> getDestinationsByRegion(@RequestParam(name = "region") String region) {
        List<Destination> destinations = destinationRepository.findDestinationByRegion(region);
        return destinations;
    }

    @PostMapping("/destination/new")
    public String createDestination(@RequestBody Destination destination) {
        destinationRepository.save(destination);
        return "Votre destination " + destination.getRegion() + " avec comme description : "
                + destination.getDescription() + " a bien été sauvegardé dans la base de données.";
    }

    @GetMapping("/destination/valid")
    public List<DestinationDTO> getDestinationNotDeleted() {
        List<DestinationDTO> destinationsdto = new ArrayList<DestinationDTO>();
        List<Destination> destinations = destinationRepository.getValidDestinations();
        for (Destination d : destinations) {
            destinationsdto.add(new DestinationDTO(d));
        }
        return destinationsdto;
    }

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

    @GetMapping("/date/{id}")
    public DatesVoyage getDatesVoyageById(@PathVariable("id") long id) {
        DatesVoyage datesVoyage = datesVoyageRepository.findById(id).get();
        return datesVoyage;
    }

    @PostMapping("voyage/new")
    public String createVoyage(@RequestBody Voyage voyage) {
        if (voyage.getDatesVoyage().getNbrePlaces() > voyage.getVoyageurs().size() && voyage.getVoyageurs().size() >= 1
                && voyage.getVoyageurs().size() <= 10) {
            voyage.getDatesVoyage().setNbrePlaces(voyage.getDatesVoyage().getNbrePlaces() - voyage.getVoyageurs().size());

            DatesVoyage dv = voyage.getDatesVoyage();
            if (dv.getNbrePlaces() == 0) {
                dv.setDeleted(true);
            }
            datesVoyageRepository.save(dv);
            voyageRepository.save(voyage);

            return "Votre voyage " + voyage.getRegion() + "avec comme description " + voyage.getDescriptif()
                    + " a bien été crée.";
        } else {
            return "Voyage non crée. Le nombre de voyageurs doit etre compris entre 1 et 9.";
        }
    }

    @GetMapping("/voyage/{id}")
    public Voyage getVoyageById(@PathVariable("id") long id) {
        Voyage voyage = voyageRepository.findById(id).get();
        return voyage;
    }

    @GetMapping("/voyage/all")
    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }


    @GetMapping("/voyage/client/{id}")
    public List<Voyage> getVoyagesByClientId(@PathVariable("id") long id) {
        List<Voyage> voyages = getAllVoyages();
        List<Voyage> voyagesDuClient = new ArrayList<>();
        for (Voyage v : voyages) {
            Client client = v.getClient();
            if (client.getId() == id) voyagesDuClient.add(v);
        }
        return voyagesDuClient;
    }

    @GetMapping("/voyage/client/nonpaye/{id}")
    public List<Voyage> getVoyagesNotPaidByClientId(@PathVariable("id") long id) {
        List<Voyage> voyages = voyageRepository.findAll();
        List<Voyage> voyagesDuClient = new ArrayList<>();
        for (Voyage v : voyages) {
            Client client = v.getClient();
            if (client.getId() == id && !v.isPaye()) voyagesDuClient.add(v);
        }
        return voyagesDuClient;
    }


    @PostMapping("/voyage/delete/{id}")
    public String deleteVoyageById(@PathVariable("id") long id) {
        voyageRepository.deleteById(id);
        return "Le voyage a bien été supprimé.";
    }

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

    @PostMapping("/signup")
    public boolean createClient(@RequestParam(name = "nom") String nom, @RequestParam(name = "password") String password)
            throws NoSuchAlgorithmException {
        boolean isAuth;
        Optional<Client> client = clientRepository.findByNom(nom);
        if (!client.isPresent()) {
            clientRepository.createNewClient(nom, password);
            isAuth = true;
            return isAuth;
        }
        isAuth = false;
        return isAuth;

    }

    @GetMapping("/client/{id}")
    public Client getUserById(@PathVariable("id") long id) {
        Client client = clientRepository.findById(id).get();

        return client;
    }

    @GetMapping("/dates/destination/{id}")
    public Destination getDestinationByDatesVoyageId(@PathVariable("id") long DatesVoyageId) {
        List<Destination> destinations = destinationRepository.getValidDestinations();
        for (Destination d : destinations) {
            List<DatesVoyage> datesvoyages = getValidDatesVoyagesByDestinationId(d.getId());
            for (DatesVoyage dv : datesvoyages) {
                if (dv.getId() == (DatesVoyageId)) {
                    return d;
                }
            }
        }
        return null;
    }

    @PostMapping("/voyage/voyageur")
    public String updateVoyageur(@RequestBody Voyageur voyageur) {
        Voyageur voyageurMisAJour = new Voyageur(voyageur.getId(), voyageur.getCivilite(), voyageur.getNom(), voyageur.getPrenom(), voyageur.getDateNaissance());
        voyageurRepository.save(voyageurMisAJour);
        return "Voyageur : " + voyageur.getNom() + " " + voyageur.getPrenom() + " mis à jour.";
    }


}
