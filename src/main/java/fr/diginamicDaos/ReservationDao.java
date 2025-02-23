package fr.diginamicDaos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Reservation;

public class ReservationDao {
	
	
	public List<Reservation> allReservation() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Reservation> queryReservation = em.createQuery("SELECT r FROM Reservation r", Reservation.class);

		return queryReservation.getResultList();
	}

}
