package fr.diginamic.entites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date dateDebut;

	private Date datefin;

	private int kilometrageDebut;

	private int kilometrageFin;

	private String commentaire;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_client")
	private Client client;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_facture")
	private Facture facture;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "vehicule_id")
	private Vehicule vehicule;

	public Reservation() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public int getKilometrageDebut() {
		return kilometrageDebut;
	}

	public void setKilometrageDebut(int kilometrageDebut) {
		this.kilometrageDebut = kilometrageDebut;
	}

	public int getKilometrageFin() {
		return kilometrageFin;
	}

	public void setKilometrageFin(int kilometrageFin) {
		this.kilometrageFin = kilometrageFin;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

}
