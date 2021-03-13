package fr.diginamicDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Vehicule;

public class VehiculeDao {

	public Vehicule findById(int id) {
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		return em.find(Vehicule.class, id);

	}

	public List<Vehicule> allVehicules() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Vehicule> queryVehicules = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class);

		return queryVehicules.getResultList();
	}

	public void modifMaintenance(Vehicule vehic, String statut) {
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Query query = em.createQuery("Update Vehicule v SET v.statut =:newstatus WHERE v.id=: id");
		query.setParameter("newstatus", statut);
		query.setParameter("id", vehic.getId());
		query.executeUpdate();
		et.commit();
		em.close();

	}

	public void modifStatut(Vehicule vehic) {
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Query query = em.createQuery("Update Vehicule v SET v.statut =:newstatus WHERE v.id=: id");
		query.setParameter("newstatus", "reservée");
		query.setParameter("id", vehic.getId());
		query.executeUpdate();
		et.commit();
		em.close();

	}

//	public int kilometrage(Vehicule vehicule) {
//		Vehicule vehicDb = findById(vehicule.getId());
//		
//		
//	}

}
