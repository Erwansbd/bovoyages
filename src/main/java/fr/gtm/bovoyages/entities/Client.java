package fr.gtm.bovoyages.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Erwan Soubeyrand, Denis Kuçuk, Jonathan Dimur.
 * @version 1.0
 * Classe Client, serialisable.
 *
 */
@Entity
@Table(name = "clients")
public class Client implements Serializable {

    /**
     * Identifiant id de type long.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_client")
    private long id;
    /**
     * Attribut nom de type String.
     */
    private String nom;
    /**
     * Attribut email de type String.
     */
    private String email;

    /**
     * Construcetur par défaut de Client.
     */
    public Client() {
    }
    
    

    /**
     * Constructeur de Client.
     * @param nom de type String.
     * @param email de type String.
     */
    public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Client(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Client other = (Client) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}








    
    
}
