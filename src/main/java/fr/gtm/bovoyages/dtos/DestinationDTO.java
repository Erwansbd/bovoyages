package fr.gtm.bovoyages.dtos;

import fr.gtm.bovoyages.entities.Destination;

import java.io.Serializable;

public class DestinationDTO implements Serializable {

    private long id;
    private String region;
    private String description;
    private boolean deleted;

    public DestinationDTO() {
    }

    public DestinationDTO(Destination destination) {
        this.id = destination.getId();
        this.region = destination.getRegion();
        this.description = destination.getDescription();
        this.deleted = destination.isRaye();
    }

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
