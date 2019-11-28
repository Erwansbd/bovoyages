package fr.gtm.bovoyages.entities;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "voyageurs")
public class Voyageur implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_voyageur")
    private long id;
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
    private String nom;
    private String prenom;
    @Past @Column(name = "date_naissance")
    private Date dateNaissance;

    public Voyageur() {
    }

    public Voyageur(Civilite civilite, String nom, String prenom) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Voyageur(Civilite civilite, String nom, String prenom, Date dateNaissance) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
