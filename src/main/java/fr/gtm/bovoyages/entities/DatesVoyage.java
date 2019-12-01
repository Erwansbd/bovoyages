package fr.gtm.bovoyages.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dates_voyages")
@Access(AccessType.FIELD)
public class DatesVoyage implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_date_voyage")
    private long id;
    @Column(name = "date_depart")
    private Date dateAller;
    @Column(name = "date_retour")
    private Date dateRetour;
    private float prixHT;
    @Column(name = "nb_places")
    private int nbrePlaces;
    @Column(name = "deleted")
    private boolean deleted;
    private boolean promotion;

    public DatesVoyage() {
    }

    public DatesVoyage(long id,Date dateAller, Date dateRetour, float prixHT, int nbrePlaces, boolean deleted, boolean promotion) {
        this.id = id;
        this.dateAller = dateAller;
        this.dateRetour = dateRetour;
        this.prixHT = prixHT;
        this.nbrePlaces = nbrePlaces;
        this.deleted = deleted;
        this.promotion = promotion;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Date getDateAller() {

        return dateAller;
    }

    public void setDateAller(Date dateAller) {

        this.dateAller = dateAller;
    }

    public Date getDateRetour() {

        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {

        this.dateRetour = dateRetour;
    }

    public float getPrixHT() {

        return prixHT;
    }

    public void setPrixHT(float prixHT) {

        this.prixHT = prixHT;
    }

    public int getNbrePlaces() {

        return nbrePlaces;
    }

    public void setNbrePlaces(int nbrePlaces) {

        this.nbrePlaces = nbrePlaces;
    }

    public boolean isDeleted() {

        return deleted;
    }

    public void setDeleted(boolean deleted) {

        this.deleted = deleted;
    }

    public boolean isPromotion() {

        return promotion;
    }

    public void setPromotion(boolean promotion) {

        this.promotion = promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatesVoyage)) return false;

        DatesVoyage that = (DatesVoyage) o;

        if (getId() != that.getId()) return false;
        if (Float.compare(that.getPrixHT(), getPrixHT()) != 0) return false;
        if (getNbrePlaces() != that.getNbrePlaces()) return false;
        if (deleted != that.deleted) return false;
        if (isPromotion() != that.isPromotion()) return false;
        if (getDateAller() != null ? !getDateAller().equals(that.getDateAller()) : that.getDateAller() != null)
            return false;
        return getDateRetour() != null ? getDateRetour().equals(that.getDateRetour()) : that.getDateRetour() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getDateAller() != null ? getDateAller().hashCode() : 0);
        result = 31 * result + (getDateRetour() != null ? getDateRetour().hashCode() : 0);
        result = 31 * result + (getPrixHT() != +0.0f ? Float.floatToIntBits(getPrixHT()) : 0);
        result = 31 * result + getNbrePlaces();
        result = 31 * result + (deleted ? 1 : 0);
        result = 31 * result + (isPromotion() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return dateAller + ", prixHT=" + prixHT +" euros";
    }
}
