package fr.gtm.bovoyages.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "images")
public class Images {
	
	@Id
	private String image;
	
	public Images() {

	}
	

	public Images(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	

}
