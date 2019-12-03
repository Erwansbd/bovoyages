package fr.gtm.bovoyages.entities;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Claasse Voyageur, serialisable.
 */
@Entity
@Table(name = "voyageurs")
public class Voyageur implements Serializable {

    /**
     * Identifiant id de type long.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_voyageur")
    private long id;
    /**
     * Attribut civilite de type Civilite.
     */
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
    /**
     * Attribut nom de type String.
     */
    private String nom;
    /**
     * Attribut prenom de type String.
     */
    private String prenom;
    /**
     * dateNaissance de type Date.
     */
    @Past @Column(name = "date_naissance")
    private Date dateNaissance;

    /**
     * Constructeur par défaut de Voyageur.
     */
    public Voyageur() {
    }

    /**
     * @param civilite de type Civilite.
     * @param nom de type String.
     * @param prenom de type String.
     * Constructeur de Voyageur. 
     */
    public Voyageur(Civilite civilite, String nom, String prenom) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * @param civilite de type Civilite.
     * @param nom de type String.
     * @param prenom de type String.
     * @param dateNaissance de type Date.
     * Constructeur de Voyageur.
     */
    public Voyageur(Civilite civilite, String nom, String prenom, Date dateNaissance) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    /**
     * @param id de type long.
     *  @param civilite de type Civilite.
     * @param nom de type String.
     * @param prenom de type String.
     * @param dateNaissance de type Date.
     * Constructeur de Voyageur.
     */
    public Voyageur(long id, Civilite civilite, String nom, String prenom, Date dateNaissance) {
    	 this.id=id;
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
