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
    private boolean raye;
    private boolean promotion;

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

    public boolean isRaye() {
        return raye;
    }

    public void setRaye(boolean raye) {
        this.raye = raye;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
}
