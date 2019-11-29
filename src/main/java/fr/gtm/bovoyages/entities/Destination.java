package fr.gtm.bovoyages.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destinations")
@Access(AccessType.FIELD)
public class Destination implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_destination")
    private long id;
    private String region;
    private String description;
    @Column(name = "deleted")
    private boolean raye;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_destination")
    private List<DatesVoyage> datesVoyages = new ArrayList<DatesVoyage>();

    public Destination(String region, String description, boolean raye) {
    }
    
    public Destination() {}

    public Destination() {
    }

    public Destination() {
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

    public boolean isRaye() {
        return raye;
    }

    public void setRaye(boolean raye) {
        this.raye = raye;
    }

    public List<DatesVoyage> getDatesVoyages() {
        return datesVoyages;
    }

    public void setDatesVoyages(List<DatesVoyage> datesVoyages) {
        this.datesVoyages = datesVoyages;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datesVoyages == null) ? 0 : datesVoyages.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (raye ? 1231 : 1237);
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		Destination other = (Destination) obj;
		if (datesVoyages == null) {
			if (other.datesVoyages != null)
				return false;
		} else if (!datesVoyages.equals(other.datesVoyages))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (raye != other.raye)
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}


}
