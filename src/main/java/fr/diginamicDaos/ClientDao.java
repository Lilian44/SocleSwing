package fr.diginamicDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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

	public List<Client> checkMailExist(String mail) {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Client> queryClients = em.createQuery("SELECT c FROM Client c JOIN c.adresse a WHERE a.mail =?1",
				Client.class);
		queryClients.setParameter(1, mail);
		return queryClients.getResultList();
	}

	public void deleteClients(int id) {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query query = em.createQuery("DELETE FROM Client c WHERE c.id=?1");
		query.setParameter(1, id);

		query.executeUpdate();
		et.commit();
		em.close();

	}

}
