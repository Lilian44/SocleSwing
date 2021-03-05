package fr.diginamic.entites;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ApplicationLocation {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("locationdb");
		EntityManager em = entityManagerFactory.createEntityManager();

		EntityTransaction tr = em.getTransaction();

	}
}
