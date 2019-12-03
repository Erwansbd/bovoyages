package fr.gtm.bovoyages.dtos;

import java.io.Serializable;
import java.util.Date;

import fr.gtm.bovoyages.dtos.DestinationDTO;
import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Voyage;

/**
 *  @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Classe VoyageDTO servant à la manipulation d'entités Voyage sans leurs collections.
 *
 */
public class VoyageDTO implements Serializable{
	
/**
 * Identifiant id de type long. 
 */
private long id;	
/**
 * Attribut region de type String.
 */
private String region;
/**
 * Attribut descriptif de type String.
 */
private String descriptif;
/**
 * Attribut datesVoyages de type DatesVoyage.
 */
private DatesVoyage datesVoyage;
/**
 * Attribut client de type Client.
 */
private Client client;

/**
 * Constructeur par défaut de DestinationDTO.
 */
public VoyageDTO() {}

/**
 * Constructeur de VoyageDTO:
 * @param voyage de type Voyage.
 */
public VoyageDTO(Voyage voyage) {
    this.id = voyage.getId();
    this.region = voyage.getRegion();
    this.descriptif = voyage.getDescriptif();
    this.datesVoyage = voyage.getDatesVoyage();
    this.client= voyage.getClient();
}

public Voyage toVoyage() {
	Voyage voyage = new Voyage(id, region, descriptif, datesVoyage, client);
    return voyage;
}



public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getRegion() {
	return region;
}

public void setRegion(String region) {
	this.region = region;
}

public String getDescriptif() {
	return descriptif;
}

public void setDescriptif(String descriptif) {
	this.descriptif = descriptif;
}

public DatesVoyage getDatesVoyage() {
	return datesVoyage;
}

public void setDatesVoyage(DatesVoyage datesVoyage) {
	this.datesVoyage = datesVoyage;
}

public Client getClient() {
	return client;
}

public void setClient(Client client) {
	this.client = client;
}




	
	
	
	
	
	
}
