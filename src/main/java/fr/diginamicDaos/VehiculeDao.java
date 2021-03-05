package fr.diginamicDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Vehicule;

public class VehiculeDao {

	public List<Vehicule> allVehicules() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Vehicule> queryVehicules = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class);

		return queryVehicules.getResultList();
	}

}
