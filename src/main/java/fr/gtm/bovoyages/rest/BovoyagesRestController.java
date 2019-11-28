package fr.gtm.bovoyages.rest;

import fr.gtm.bovoyages.dtos.DestinationDTO;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BovoyagesRestController {

    @Autowired
    DestinationRepository destinationRepository;

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

    @PostMapping("/destination/new")
    public String createDestination(@RequestBody DestinationDTO destinationDTO) {
        destinationRepository.save(destinationDTO.toDestination());
        return "Votre destination " +destinationDTO.getRegion()+ " avec comme description : " +destinationDTO.getDescription()+ " a bien été sauvegardé dans la base de données.";
    }

}
