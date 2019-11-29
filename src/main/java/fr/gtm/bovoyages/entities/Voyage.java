package fr.gtm.bovoyages.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voyages")
public class Voyage implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_voyage")
    private long id;
    private String region;
    private String descriptif;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_dates_voyage")
    private DatesVoyage datesVoyage;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_client")
    private Client client;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "voyages_voyageurs",
                joinColumns = @JoinColumn(name = "fk_voyage"),
                inverseJoinColumns = @JoinColumn(name = "fk_voyageur"))
    private List<Voyageur> voyageurs = new ArrayList<Voyageur>();
    
    public Voyage() {}
    
    

    public Voyage(String region, String descriptif, DatesVoyage datesVoyage, Client client, List<Voyageur> voyageurs) {
		super();
		this.region = region;
		this.descriptif = descriptif;
		this.datesVoyage = datesVoyage;
		this.client = client;
		this.voyageurs = voyageurs;
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

    public List<Voyageur> getVoyageurs() {
        return voyageurs;
    }

    public void setVoyageurs(List<Voyageur> voyageurs) {
        this.voyageurs = voyageurs;
    }
}
