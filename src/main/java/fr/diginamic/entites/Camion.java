package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Camion extends Vehicule {

	@Id
	private int id;

	private int volume;

	public Camion() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}
