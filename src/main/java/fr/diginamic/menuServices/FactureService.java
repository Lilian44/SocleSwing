package fr.diginamic.menuServices;

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
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Facture;
import fr.diginamic.entites.Reservation;
import fr.diginamic.entites.Type;
import fr.diginamicDaos.ChiffreAffaireDao;
import fr.diginamicDaos.FactureDao;
import fr.diginamicDaos.ReservationDao;

public class FactureService extends MenuService {

	@Override
	public void traitement() {
		// TODO Auto-generated method stub

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		FactureDao fact = new FactureDao();
		ReservationDao allResa = new ReservationDao();
		List<Reservation> listResa = allResa.allReservation();
		List<Selectable> reservations = new ArrayList<>();
		reservations.addAll(listResa);

		Form form = new Form();

		form.addInput(new TextField("Type de reglement :", "reglement"));
		form.addInput(new ComboBox("Liste des reservations:", "reservations", reservations, reservations.get(0)));
		boolean valide = console.input("creation facture", form, null);

		Reservation res = form.getValue("reservations");
		long diff = res.getDatefin().getTime() - res.getDateDebut().getTime();
		float result = (diff / (1000 * 60 * 60 * 24));
		List<Type> montantJour = fact.montantFacture(res);


		Facture facture = new Facture(4, result * montantJour.get(0).getMontantJour(), form.getValue("reglement"),
				form.getValue("reservations"));
		ChiffreAffaireDao solde = new ChiffreAffaireDao();
		solde.solde(result * montantJour.get(0).getMontantJour());

		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>facture</td><td>montant de la facture</td></tr>";
		html += "<tr>" + "  <td width='150px'>" + facture.getId() + "</td>" + " <td width='150px'>"
				+ result * montantJour.get(0).getMontantJour() + "</td>" + "</tr>";
		console.print(html);
		em.persist(facture);
		et.commit();
		em.close();
	}

}
