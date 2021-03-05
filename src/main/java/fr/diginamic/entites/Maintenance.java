package fr.diginamic.entites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Maintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date debutMaintenance;

	private Date finMaintenance;

	private double coutMaintenance;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "vehic_id")
	private Vehicule vehicule;

	public Maintenance() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDebutMaintenance() {
		return debutMaintenance;
	}

	public void setDebutMaintenance(Date debutMaintenance) {
		this.debutMaintenance = debutMaintenance;
	}

	public Date getFinMaintenance() {
		return finMaintenance;
	}

	public void setFinMaintenance(Date finMaintenance) {
		this.finMaintenance = finMaintenance;
	}

	public double getCoutMaintenance() {
		return coutMaintenance;
	}

	public void setCoutMaintenance(double coutMaintenance) {
		this.coutMaintenance = coutMaintenance;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
	

}
