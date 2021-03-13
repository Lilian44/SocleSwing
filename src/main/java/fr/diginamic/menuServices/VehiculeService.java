package fr.diginamic.menuServices;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.ComboBox;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Input;
import fr.diginamic.composants.ui.Selectable;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Type;
import fr.diginamic.entites.Vehicule;
import fr.diginamic.entites.Voiture;
import fr.diginamicDaos.ClientDao;
import fr.diginamicDaos.TypeDao;
import fr.diginamicDaos.VehiculeDao;

public class VehiculeService extends MenuService {

	private EntityManager em;

	public VehiculeService(EntityManager em) {
		super();
		this.em = em;
	}

	public VehiculeService() {
	}

	public void traitement() {

		console.clear();
//		String html = "<a class='btn-blue' href='ajouterVoiture()'><img width=25 src='images/pencil-blue-xs.png'></a>";
//		console.print(html);
//
//		console.print("<a class='btn-blue' href='listeVehicule()'> liste des vehicules</a>");

		console.print("<table cellspacing=0>"
				+ "<tr class='bg-green'><td width='150px'>Ajouter une voiture</td><td width='150px'>liste des voitures</td></tr>"

				+ "<tr>" + "  <td width='150px'><a class='btn-blue' href='ajouterVoiture()'> Nouvelle voiture </a></td>"
				+ "</td>" + "  <td width='150px'><a class='btn-blue' href='listeVehicule()'> liste des voitures</a></td>"
				+ "</tr>" + "</table>");

	}

	public void listeVehicule() {
		console.clear();
		EntityManager em = Application.entityManagerFactory.createEntityManager();
//		TypedQuery<Vehicule> queryVehicules = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class);
//
//		List<Vehicule> vehic = queryVehicules.getResultList();
		VehiculeDao allvehic = new VehiculeDao();
		List<Vehicule> listVehicules = allvehic.allVehicules();
		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Modele</td><td>immatriculation</td></tr>";
		for (Vehicule v : listVehicules) {
			html += "<tr>" + "  <td><a class='btn-blue' href='modifierVehicule(" + v.getId()
					+ ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='supprimerVehicule(" + v.getId()
					+ ")'><img width=25 src='images/trash-red-xs.png'></a></td>" + "  <td width='150px'>"
					+ v.getModele() + "</td>" + "  <td width='150px'>" + v.getImmatriculation() + "</td>" + "</tr>";
		}
		html += "</table>";

		console.print(html);
//		for (int i = 0; i < vehic.size(); i++) {
//			console.println(vehic.get(i).toString(), Color.RED);
//		}

	}

	public void ajouterVoiture() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Form form = new Form();

		form.addInput(new TextField("commentaire :", "commentaire"));
		form.addInput(new TextField("Immatriculation:", "immatriculation"));
		form.addInput(new TextField("Kilometrage:", "kilometrage"));
		form.addInput(new TextField("Nombre de place:", "nbrPlaces"));
		form.addInput(new TextField("Modele:", "modele"));

		TypeDao alltypes = new TypeDao();
		List<Type> listType = alltypes.allTypes();

		List<Selectable> types = new ArrayList<>();

		types.addAll(listType);

		// Récupéation des informations saisies
//		if (valide) {
//			console.print("Vous vous <b>appelez</b> ").println(
//					"<span style='color:red'>" + form.getValue("champ2") + " " + form.getValue("champ1") + "</span>");
//			console.println("Date de naissance :" + form.getValue("dateNaissance"));
//			console.println("Voiture sélectionnée :" + form.getValue("vehicule"));
//		}

		form.addInput(new ComboBox("Liste des types:", "types", types, types.get(0)));
		VehiculeValidator validVehic = new VehiculeValidator();
		boolean valide = console.input("nouveau v�hicule", form, validVehic);

		Type t = form.getValue("types");

		Voiture voiture = new Voiture(form.getValue("immatriculation"), Integer.parseInt(form.getValue("kilometrage")),
				form.getValue("commentaire"), form.getValue("modele"), Integer.parseInt(form.getValue("nbrPlaces")), t);
		voiture.setStatut("disponible");
		em.persist(voiture);
		et.commit();
		em.close();

	}

	public void modifierVehicule(Long id) {
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		console.clear();


		Integer newid = id.intValue();
		Vehicule vehiculeModifier = em.find(Vehicule.class, newid);

		Form form = new Form();

		form.addInput(new TextField("commentaire :", "commentaire"));

		form.addInput(new TextField("Kilometrage:", "kilometrage"));
		boolean valide = console.input("modification v�hicule", form, null);

		vehiculeModifier.setCommentaire(form.getValue("commentaire"));
		vehiculeModifier.setKilometrage(Integer.parseInt(form.getValue("kilometrage")));

		et.commit();
		em.close();

	}

}
