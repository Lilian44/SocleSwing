package fr.diginamic.menuServices;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Adresse;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Reservation;
import fr.diginamic.entites.Vehicule;
import fr.diginamicDaos.ClientDao;
import fr.diginamicDaos.ReservationDao;
import fr.diginamicDaos.VehiculeDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationService extends MenuService {

	@Override
	public void traitement() {
		// TODO Auto-generated method stub
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Form form = new Form();
		form.addInput(new TextField("commentaire :", "commentaire"));
		form.addInput(new DateField("Début de la réservation :", "dateDebut"));
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new DateField("Date de rendu du véhicule:", "dateFin"));

		ClientDao allClients = new ClientDao();
		List<Client> listClient = allClients.allClients();
		List<Selectable> clients = new ArrayList<>();

		VehiculeDao allVehicule = new VehiculeDao();
		List<Vehicule> listVehicule = allVehicule.allVehicules();
		List<Selectable> vehicules = new ArrayList<>();

		clients.addAll(listClient);
		vehicules.addAll(listVehicule);

//		VehiculeDao allvehic = new VehiculeDao();
//		List<Vehicule> listVehic = allvehic.allVehicules();
//		List<Selectable> vehicules = new ArrayList<>();
//		for (int i = 0; i < listVehic.size(); i++) {
//			vehicules.add((Selectable) new Vehicule(listVehic.get(i).getModele()));
//			
//		}
//		
//		form.addInput(new ComboBox("Liste de véhicules:", "vehicule", vehicules, vehicules.get(2)));
		form.addInput(new ComboBox("Liste des clients:", "clients", clients, clients.get(0)));
		form.addInput(new ComboBox("Liste des véhicules:", "vehicules", vehicules, vehicules.get(0)));

		
		ReservationValidator validReservation = new ReservationValidator();
		boolean valide = console.input("Demande d'informations", form, validReservation);
		Vehicule v = form.getValue("vehicules");

		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date dateDebut;
		Date dateFin;
		try {
			dateDebut = sourceFormat.parse(form.getValue("dateDebut"));
			dateFin = sourceFormat.parse(form.getValue("dateFin"));

			Reservation resa = new Reservation(dateDebut, dateFin, v.getKilometrage(), form.getValue("commentaire"),
					form.getValue("clients"), form.getValue("vehicules"));
			allVehicule.modifStatut(v);
			
			em.persist(resa);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		et.commit();
		em.close();

	

	}

}
