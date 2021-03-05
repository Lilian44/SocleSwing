package fr.diginamic.entites;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import fr.diginamic.composants.ui.Selectable;

@Entity
public class Client implements Selectable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	private String prenom;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_adresse")
	private Adresse adresse;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_reservation")
	private Reservation reservation;

	public Client(String nom, String prenom, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;

	}

	public Client(Client client) {
	}

	public Client() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "  nom=" + nom + " prenom=" + prenom ;
	}

}
