package fr.diginamicDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Reservation;
import fr.diginamic.entites.Type;

public class FactureDao {

	public List<Type> montantFacture(Reservation reservation) {

		int id = reservation.getId();
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Type> queryType = em.createQuery("SELECT t FROM Type t JOIN t.voitures v JOIN v.reservation r WHERE r.id=1  ", Type.class);
//		queryType.setParameter(1, id);
		
		
		return queryType.getResultList();
	}

}
