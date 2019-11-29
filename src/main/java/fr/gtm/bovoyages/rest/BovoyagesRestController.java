package fr.gtm.bovoyages.rest;

import fr.gtm.bovoyages.dtos.DestinationDTO;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.repositories.DatesVoyageRepository;
import fr.gtm.bovoyages.repositories.DestinationRepository;
import fr.gtm.bovoyages.repositories.VoyageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BovoyagesRestController {

    @Autowired
    DestinationRepository destinationRepository;
    @Autowired 
    VoyageRepository voyageRepository;
    @Autowired
    DatesVoyageRepository datesVoyagesRepository;
    
    public static final Logger LOG = Logger.getLogger("Bovoyages");

    @GetMapping("/destination/all")
    public List<DestinationDTO> getAllDestinations() {
        List<Destination> destinationList = destinationRepository.findAll();
        List<DestinationDTO> destinationDTOList = new ArrayList<DestinationDTO>();
        for(Destination destination : destinationList) {
            destinationDTOList.add(new DestinationDTO(destination));
        }
        return destinationDTOList;
    }

    @GetMapping("/destination/{id}")
    public Destination getDestinationById(@PathVariable("id")long id) {
        Destination destination = destinationRepository.findById(id).get();
        return destination;
    }

//    @PostMapping("/destination/new")
//    public String createDestination(@RequestBody DestinationDTO destinationDTO) {
//        destinationRepository.save(destinationDTO.toDestination(destinationDTO));
//        return "Votre destination " +destinationDTO.getRegion()+ " avec comme description : " +destinationDTO.getDescription()+ " a bien été sauvegardé dans la base de données.";
//    }
    
    @PostMapping("/destination/new")
    public String createDestination(@RequestBody DestinationDTO destinationDTO) {
        destinationRepository.save(destinationDTO.toDestination());
        return "Votre destination " +destinationDTO.getRegion()+ " avec comme description : " +destinationDTO.getDescription()+ " a bien été sauvegardé dans la base de données.";
    }
    
    @GetMapping("/destination/valid")
    public List<DestinationDTO> getDestinationNotDeleted() {
    	List<DestinationDTO> destinationsdto = new ArrayList<DestinationDTO>();
		List<Destination> destinations = destinationRepository.getValidDestinations();
		for(Destination d : destinations) {
			destinationsdto.add(new DestinationDTO(d));
		}
		return destinationsdto;
    }
    
    @GetMapping("/destination/dates/valid/{id}")
    public List<DatesVoyage> getValidDatesVoyagesByDestinationId(@PathVariable("id") long id) {
    	List<DatesVoyage> datesVoyages = new ArrayList<>();
    	Destination destination = destinationRepository.getDestinationWithDatesById(id);
    	for (DatesVoyage d : destination.getDatesVoyages() ) {
    		if(!d.isDeleted()) datesVoyages.add(d);
    	}
    	return datesVoyages;
    }
    
    @PostMapping("voyage/new")
    public String createVoyage(@RequestBody Voyage voyage) {
    	if (voyage.getDatesVoyage().getNbrePlaces() > voyage.getVoyageurs().size() && voyage.getVoyageurs().size() >= 1
				&& voyage.getVoyageurs().size() <= 10) {
			LOG.info(" >>>>>" + voyage.getDatesVoyage().getNbrePlaces());
			voyage.getDatesVoyage()
					.setNbrePlaces(voyage.getDatesVoyage().getNbrePlaces() - voyage.getVoyageurs().size());
			LOG.info(">>>>>" + voyage.getVoyageurs().size());
			LOG.info(" >>>>" + voyage.getDatesVoyage().getNbrePlaces());
			DatesVoyage dv = voyage.getDatesVoyage();
            //updateDV(dv);
			if (dv.getNbrePlaces()==0) {
			dv.setDeleted(true);
			}
			datesVoyagesRepository.save(dv);
			LOG.info(" >>>>" + voyage.getDatesVoyage().getNbrePlaces());
//			voyageRepository.updateDatesVoyageByVoyageId(voyage.getId());
			//voyageRepository.save(voyage);
			return "Votre voyage " + voyage.getRegion() +"avec comme description " +voyage.getDescriptif() +" a bien été crée.";
    	}
			else {
    		return "Voyage non crée. Le nombre de voyageurs doit etre compris entre 1 et 9.";
    	} 	
    }
    	

    	
    	
    @GetMapping("/voyage/{id}")
    public Voyage getVoyageById(@PathVariable("id")long id) {
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
	
	//datesVoyagesRepository.save(datesVoyage);
	
//}

}
