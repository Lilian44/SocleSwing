package fr.diginamicDaos;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.diginamic.entites.Application;

public class ChiffreAffaireDao {

	public void solde(double solde) {
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query query = em.createQuery("UPDATE Entreprise e SET e.solde =e.solde +" + solde + "");

		query.executeUpdate();
		et.commit();
		em.close();

	}
	
	public void debit(double solde) {
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query query = em.createQuery("UPDATE Entreprise e SET e.solde =e.solde -" + solde + "");

		query.executeUpdate();
		et.commit();
		em.close();

	}

}
