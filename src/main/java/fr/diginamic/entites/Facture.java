package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Facture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int numeroFacture;

	private String typeReglement;

	public Facture() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroFacture() {
		return numeroFacture;
	}

	public void setNumeroFacture(int numeroFacture) {
		this.numeroFacture = numeroFacture;
	}

	public String getTypeReglement() {
		return typeReglement;
	}

	public void setTypeReglement(String typeReglement) {
		this.typeReglement = typeReglement;
	}
	
	

}
