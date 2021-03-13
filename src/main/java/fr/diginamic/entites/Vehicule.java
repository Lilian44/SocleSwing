package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import fr.diginamic.composants.ui.Selectable;

import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicule implements Selectable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String marque;

	private String modele;

	private String immatriculation;

	private String statut;

	private int kilometrage;

	private String commentaire;

	@OneToMany(mappedBy = "vehicule")
	private List<Reservation> reservation = new ArrayList<>();

	@OneToMany(mappedBy = "vehicule")
	private List<Maintenance> maintenances = new ArrayList<>();

	public Vehicule(String immatriculation, int kilometrage, String commentaire,String modele) {
		super();

		this.immatriculation = immatriculation;
		this.kilometrage = kilometrage;
		this.commentaire = commentaire;
		this.modele=modele;

	}

	public Vehicule() {
	}

	public Vehicule(String modele) {
		super();
		this.modele = modele;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	public List<Maintenance> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}

	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", marque=" + marque + ", modele=" + modele + ", immatriculation="
				+ immatriculation + ", statut=" + statut + ", kilometrage=" + kilometrage + ", commentaire="
				+ commentaire + ", reservation=" + reservation + ", maintenances=" + maintenances + "]";
	}

}
