package fr.gtm.bovoyages.dtos;

import fr.gtm.bovoyages.entities.Destination;

import java.io.Serializable;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Classe DestinationDTO servant à la manipulation d'entités Destination sans leurs collections.
 *
 */
public class DestinationDTO implements Serializable {

    /**
     * Identifiant id de type long. 
     */
    private long id;
    /**
     * Attribut region de type String.
     */
    private String region;
    /**
     * Attribut description de type String
     */
    private String description;
    /**
     * Attribut region de type boolean.
     */
    private boolean deleted;

    /**
     * Constructeur par défaut de DestinationDTO
     */
    public DestinationDTO() {
    }

    /**
     * Constructeur de DestinationDTO:
     * @param destination de type Destination
     */
    public DestinationDTO(Destination destination) {
        this.id = destination.getId();
        this.region = destination.getRegion();
        this.description = destination.getDescription();
        this.deleted = destination.isRaye();
    }

    /**
     * Methode servant à transformer un objet de typde DestinationDTO en Destination
     * @param destinationdto de type DestinationDTP
     * @return une destination de type Destination
     */
    public Destination toDestination(DestinationDTO destinationdto) {
    	String region = destinationdto.getRegion();
    	String description = destinationdto.getDescription();
    	boolean deleted = destinationdto.isDeleted();
        Destination destination = new Destination(region,description,deleted);
        destination.setId(id);
        return destination;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



}
