package fr.diginamic.entites;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity

public class Adresse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String numeroRue;

	private String libelle;

	private String codePostal;

	private String ville;

	private int telephone;

	private String mail;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_client")
	private Client client;

	public Adresse() {
	}

	public Adresse(String numeroRue, String libelle, String codePostal, String ville, int telephone, String mail) {
		super();
		this.numeroRue = numeroRue;
		this.libelle = libelle;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.mail = mail;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroRue() {
		return numeroRue;
	}

	public void setNumeroRue(String numeroRue) {
		this.numeroRue = numeroRue;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", numeroRue=" + numeroRue + ", libelle=" + libelle + ", codePostal=" + codePostal
				+ ", ville=" + ville + ", telephone=" + telephone + ", mail=" + mail + ", client=" + client + "]";
	}

}
