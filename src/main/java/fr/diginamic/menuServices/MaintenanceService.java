package fr.diginamic.menuServices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Maintenance;
import fr.diginamic.entites.Reservation;
import fr.diginamic.entites.Vehicule;
import fr.diginamicDaos.ChiffreAffaireDao;
import fr.diginamicDaos.VehiculeDao;

public class MaintenanceService extends MenuService {

	@Override
	public void traitement() {
		// TODO Auto-generated method stub

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Form form = new Form();
		form.addInput(new TextField("Cout maintenance :", "cout"));
		form.addInput(new DateField("Début de la maintenance :", "dateDebut"));
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new DateField("Fin de la maintenance:", "dateFin"));

		VehiculeDao allVehicule = new VehiculeDao();
		List<Vehicule> listVehicule = allVehicule.allVehicules();
		List<Selectable> vehicules = new ArrayList<>();

		vehicules.addAll(listVehicule);

		form.addInput(new ComboBox("Liste des véhicules:", "vehicules", vehicules, vehicules.get(0)));

		MaintenanceValidator maintValidator = new MaintenanceValidator();
		boolean valide = console.input("Mise en mainteance d'un vehicule", form, maintValidator);

		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		Vehicule v = form.getValue("vehicules");

		Date dateDebut;
		Date dateFin;
		try {
			dateDebut = sourceFormat.parse(form.getValue("dateDebut"));
			dateFin = sourceFormat.parse(form.getValue("dateFin"));

			Maintenance maint = new Maintenance(dateDebut, dateFin, Double.valueOf(form.getValue("cout")), v);
			v.setStatut("en maintenance");
			console.print("<h1>" + v.getId() + " </h1>");
			allVehicule.modifMaintenance(v, "en Maintenance");
			ChiffreAffaireDao solde = new ChiffreAffaireDao();
			solde.solde(maint.getCoutMaintenance());
			em.persist(maint);
			et.commit();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		em.close();

	}

}
