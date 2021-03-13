package fr.diginamicDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Type;

public class TypeDao {

	public List<Type> allTypes() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Type> queryTypes = em.createQuery("SELECT t FROM Type t", Type.class);

		return queryTypes.getResultList();
	}



}
