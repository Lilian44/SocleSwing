package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Voiture extends Vehicule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int nbrePlace;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private Type type;

	public Voiture() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbrePlace() {
		return nbrePlace;
	}

	public void setNbrePlace(int nbrePlace) {
		this.nbrePlace = nbrePlace;
	}

}
