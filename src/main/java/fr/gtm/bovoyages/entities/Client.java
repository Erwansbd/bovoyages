package fr.gtm.bovoyages.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_client")
    private long id;
    private String nom;
    private String email;

    public Client() {
    }
    
    

    public Client(String nom, String email) {
        this.nom = nom;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (getId() != client.getId()) return false;
        if (getNom() != null ? !getNom().equals(client.getNom()) : client.getNom() != null) return false;
        return getEmail() != null ? getEmail().equals(client.getEmail()) : client.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getNom() != null ? getNom().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
