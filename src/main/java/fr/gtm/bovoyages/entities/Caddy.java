package fr.gtm.bovoyages.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.annotation.SessionScope;

@SessionScope
public class Caddy implements Serializable {
	
private Client client;	
private Voyage voyage;
private double prixHT;
private List<Voyage> voyages = new ArrayList<Voyage>();
//private List<VoyageDTO> voyageDtos = new ArrayList<VoyageDTO>();



public Caddy() {}

public Voyage getVoyage() {
	return voyage;
}

public void setVoyage(Voyage voyage) {
	this.voyage = voyage;
}

public double getPrixHT() {
	return prixHT;
}

public void setPrixHT(double prixHT) {
	this.prixHT = prixHT;
}



public Client getClient() {
	return client;
}

public void setClient(Client client) {
	this.client = client;
}

public List<Voyage> getVoyages() {
	return voyages;
}

public void setVoyages(List<Voyage> voyages) {
	this.voyages = voyages;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((client == null) ? 0 : client.hashCode());
	long temp;
	temp = Double.doubleToLongBits(prixHT);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((voyage == null) ? 0 : voyage.hashCode());
	result = prime * result + ((voyages == null) ? 0 : voyages.hashCode());
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
	Caddy other = (Caddy) obj;
	if (client == null) {
		if (other.client != null)
			return false;
	} else if (!client.equals(other.client))
		return false;
	if (Double.doubleToLongBits(prixHT) != Double.doubleToLongBits(other.prixHT))
		return false;
	if (voyage == null) {
		if (other.voyage != null)
			return false;
	} else if (!voyage.equals(other.voyage))
		return false;
	if (voyages == null) {
		if (other.voyages != null)
			return false;
	} else if (!voyages.equals(other.voyages))
		return false;
	return true;
}


}
