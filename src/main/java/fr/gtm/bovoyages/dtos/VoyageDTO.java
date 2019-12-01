package fr.gtm.bovoyages.dtos;

import java.io.Serializable;
import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Voyage;

public class VoyageDTO implements Serializable {

    private long id;
    private String region;
    private String descriptif;
    private DatesVoyage datesVoyage;
    private Client client;

    public VoyageDTO() {
    }

    public VoyageDTO(Voyage voyage) {
        this.id = voyage.getId();
        this.region = voyage.getRegion();
        this.descriptif = voyage.getDescriptif();
        this.datesVoyage = voyage.getDatesVoyage();
        this.client = voyage.getClient();
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