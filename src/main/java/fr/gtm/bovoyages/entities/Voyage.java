package fr.gtm.bovoyages.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Classe Voyage, serialisable.
 */
@Entity
@Table(name = "voyages")
public class Voyage implements Serializable {

    /**
     * Identifiant id de type long.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_voyage")
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
     * Attribut paye de type boolean.
     */
    private boolean paye;
    /**
     * Attribut datesVoyage de type DatesVoyage.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_dates_voyage")
    private DatesVoyage datesVoyage;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_client")
    private Client client;

    /**
     * Attribut voyageurs de type List<Voyageur>.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "voyages_voyageurs",
                joinColumns = @JoinColumn(name = "fk_voyage"),
                inverseJoinColumns = @JoinColumn(name = "fk_voyageur"))
    private List<Voyageur> voyageurs = new ArrayList<Voyageur>();
    
    /**
     * Constructeur par défaut de Voyage.
     */
    public Voyage() {}
    
    

    /**
     * @param region de type String.
     * @param descriptif de type String.
     * @param datesVoyage de type DatesVoyage.
     * @param client de type Client.
     * @param voyageurs de type List<Voyageur>.
     * Constructeur de Voyage.
     */
    public Voyage(String region, String descriptif, DatesVoyage datesVoyage, Client client, List<Voyageur> voyageurs) {
		super();
		this.region = region;
		this.descriptif = descriptif;
		this.datesVoyage = datesVoyage;
		this.client = client;
		this.voyageurs = voyageurs;
	}
    
    public Voyage(String region, String descriptif, boolean paye, DatesVoyage datesVoyage, Client client, List<Voyageur> voyageurs) {
 		this.region = region;
 		this.descriptif = descriptif;
 		this.paye = paye;
 		this.datesVoyage = datesVoyage;
 		this.client = client;
 		this.voyageurs = voyageurs;
 	}






	public Voyage(long id, String region, String descriptif, DatesVoyage datesVoyage, Client client) {
		this.id = id;
		this.region = region;
		this.descriptif = descriptif;
		this.datesVoyage = datesVoyage;
		this.client = client;
	}



	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPaye() {
		return paye;
	}



	public void setPaye(boolean paye) {
		this.paye = paye;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((datesVoyage == null) ? 0 : datesVoyage.hashCode());
		result = prime * result + ((descriptif == null) ? 0 : descriptif.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (paye ? 1231 : 1237);
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((voyageurs == null) ? 0 : voyageurs.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voyage other = (Voyage) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (datesVoyage == null) {
			if (other.datesVoyage != null)
				return false;
		} else if (!datesVoyage.equals(other.datesVoyage))
			return false;
		if (descriptif == null) {
			if (other.descriptif != null)
				return false;
		} else if (!descriptif.equals(other.descriptif))
			return false;
		if (id != other.id)
			return false;
		if (paye != other.paye)
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (voyageurs == null) {
			if (other.voyageurs != null)
				return false;
		} else if (!voyageurs.equals(other.voyageurs))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Voyage id=" + id + ", region=" + region + ", le=" + datesVoyage + ".";
	}
 
    
}
