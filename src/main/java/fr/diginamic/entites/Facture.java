package fr.diginamic.entites;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity

public class Facture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int numeroFacture;

	private double montant;

	private String typeReglement;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "id_reservation")
	private Reservation reservation;

	public Facture() {
	}

	public Facture(int numeroFacture, double montant, String typeReglement, Reservation reservation) {
		super();
		this.numeroFacture = numeroFacture;
		this.montant = montant;
		this.typeReglement = typeReglement;
		this.reservation = reservation;
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
