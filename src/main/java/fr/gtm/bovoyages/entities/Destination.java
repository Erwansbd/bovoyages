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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination)) return false;

        Destination that = (Destination) o;

        if (getId() != that.getId()) return false;
        if (isRaye() != that.isRaye()) return false;
        if (getRegion() != null ? !getRegion().equals(that.getRegion()) : that.getRegion() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        return getDatesVoyages() != null ? getDatesVoyages().equals(that.getDatesVoyages()) : that.getDatesVoyages() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (isRaye() ? 1 : 0);
        result = 31 * result + (getDatesVoyages() != null ? getDatesVoyages().hashCode() : 0);
        return result;
    }
}
