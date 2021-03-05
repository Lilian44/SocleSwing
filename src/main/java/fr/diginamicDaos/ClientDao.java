package fr.diginamicDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Vehicule;

public class ClientDao {

	public List<Client> allClients() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Client> queryClients = em.createQuery("SELECT c FROM Client c", Client.class);

		return queryClients.getResultList();
	}

}
