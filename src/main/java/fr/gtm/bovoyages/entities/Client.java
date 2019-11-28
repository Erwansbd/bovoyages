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

    public Client() {
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
}
