package fr.diginamic.entites;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.composants.AbstractApplication;
import fr.diginamic.menuServices.ClientService;
import fr.diginamic.menuServices.ReservationService;
import fr.diginamic.menuServices.TypeService;
import fr.diginamic.menuServices.VehiculeService;
import fr.diginamic.services.exemples.AideService;
import fr.diginamic.services.exemples.Exemple1Service;
import fr.diginamic.services.exemples.Exemple2Service;
import fr.diginamic.services.exemples.Exemple3Service;
import fr.diginamic.services.exemples.Exemple4Service;
import fr.diginamic.services.exemples.Exemple5Service;
import fr.diginamic.services.exemples.Exemple6Service;

/**
 * Fenêtre principale qui porte les principaux composants graphiques de
 * l'application:<br>
 * - les boutons du menu,<br>
 * - le panneau d'affichage des résultats<br>
 * 
 * @author RichardBONNAMY
 *
 */
public class Application extends AbstractApplication {

	/** serialVersionUID */
	private static final long serialVersionUID = 6755835482616236832L;
	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("locationdb");

	/**
	 * Constructeur
	 * 
	 * @param title titre
	 */
	public Application(String title) {
		super(title);
	}

	/**
	 * Code principal
	 * 
	 */
	public void main() {

		EntityManager em = entityManagerFactory.createEntityManager();

		VehiculeService vehicule = new VehiculeService(em);
		ClientService client = new ClientService(em);
		TypeService type = new TypeService();
		ReservationService resa = new ReservationService();

		addMenu(1, "vehicules");
		addMenu(2, "clients ");
		addMenu(3, "type");
		addMenu(4, "reservation");

		addMenuOption(1, "vehicules", vehicule);
		addMenuOption(2, "clients", client);
		addMenuOption(3, "type", type);
		addMenuOption(4, "reservation", resa);

//		
//		addMenuOption(2, "Liste des clients", new AideService());
//		
//		addMenuOption(3, "Exemple 1 - Titres", new Exemple1Service());
//		addMenuOption(3, "Exemple 2 - Textes de couleur", new Exemple2Service());
//		addMenuOption(3, "Exemple 3 - Table", new Exemple3Service());
//		addMenuOption(3, "Exemple 4 - Table avec liens vers méthodes", new Exemple4Service());
//		addMenuOption(3, "Exemple 5 - Table dynamique", new VehiculeDao(em));
//		addMenuOption(3, "Exemple 6 - Formulaire", new VehiculeDao(em));
	}
}