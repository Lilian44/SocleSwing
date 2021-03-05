package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entreprise {

	@Id
	private int id;

	private double solde;

	public Entreprise() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

}
