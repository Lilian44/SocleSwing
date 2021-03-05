package fr.diginamic.menuServices;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Adresse;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Vehicule;
import fr.diginamicDaos.ClientDao;
import fr.diginamicDaos.ReservationDao;
import fr.diginamicDaos.VehiculeDao;

public class ReservationService extends MenuService {

	@Override
	public void traitement() {
		// TODO Auto-generated method stub
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Form form = new Form();

		form.addInput(new TextField("Début de la réservation :", "dateDebut"));
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("Date de rendu du véhicule:", "dateFin"));

		ClientDao allClients = new ClientDao();
		List<Client> listClient = allClients.allClients();
		List<Selectable> clients = new ArrayList<>();
		console.print("<h1>" + listClient.get(0) + "</h1>");

		for (int i = 0; i < listClient.size(); i++) {
			console.print("<h1>plop</h1>");
			clients.add(new Client(listClient.get(i).getNom(), listClient.get(i).getPrenom(),
					listClient.get(i).getAdresse()));

		}
//		VehiculeDao allvehic = new VehiculeDao();
//		List<Vehicule> listVehic = allvehic.allVehicules();
//		List<Selectable> vehicules = new ArrayList<>();
//		for (int i = 0; i < listVehic.size(); i++) {
//			vehicules.add((Selectable) new Vehicule(listVehic.get(i).getModele()));
//			
//		}
//		
//		form.addInput(new ComboBox("Liste de véhicules:", "vehicule", vehicules, vehicules.get(2)));
		form.addInput(new ComboBox("Liste des clients:", "clients", clients, clients.get(2)));

		boolean valide = console.input("Demande d'informations", form, null);

	}

}
