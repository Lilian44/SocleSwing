package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nomType;

	private double montantJour;

	private double caution;

	@OneToMany(mappedBy = "type")
	private List<Voiture> voitures = new ArrayList<>();

	public Type() {
	}

	public Type(String nomType, double montantJour, double caution) {
		super();
		this.nomType = nomType;
		this.montantJour = montantJour;
		this.caution = caution;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomType() {
		return nomType;
	}

	public void setNomType(String nomType) {
		this.nomType = nomType;
	}

	public double getMontantJour() {
		return montantJour;
	}

	public void setMontantJour(double montantJour) {
		this.montantJour = montantJour;
	}

	public double getCaution() {
		return caution;
	}

	public void setCaution(double caution) {
		this.caution = caution;
	}

}
