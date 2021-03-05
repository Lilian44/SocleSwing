package fr.diginamic.menuServices;

import java.awt.Color;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.Input;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Vehicule;

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
		String html = "<a class='btn-blue' href='ajouter()'><img width=25 src='images/pencil-blue-xs.png'></a>";
		console.print(html);
//		addCar();
		console.print("<a class='btn-blue' href='listeVehicule()'> liste des vehicules</a>");

	}

	public void listeVehicule() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Vehicule> queryVehicules = em.createQuery("SELECT v FROM Vehicule v", Vehicule.class);

		List<Vehicule> vehic = queryVehicules.getResultList();

		for (int i = 0; i < vehic.size(); i++) {
			console.println(vehic.get(i).toString(), Color.RED);
		}

	}

	public void ajouter() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Form form = new Form();

		form.addInput(new TextField("commentaire :", "commentaire"));
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("Immatriculation:", "immatriculation"));
		form.addInput(new TextField("Kilometrage:", "kilometrage"));

		boolean valide = console.input("Demande d'informations", form, null);

		// Récupéation des informations saisies
//		if (valide) {
//			console.print("Vous vous <b>appelez</b> ").println(
//					"<span style='color:red'>" + form.getValue("champ2") + " " + form.getValue("champ1") + "</span>");
//			console.println("Date de naissance :" + form.getValue("dateNaissance"));
//			console.println("Voiture sélectionnée :" + form.getValue("vehicule"));
//		}

		Vehicule vehic = new Vehicule(form.getValue("immatriculation"), Integer.parseInt(form.getValue("kilometrage")),
				form.getValue("commentaire"));
		console.print("<h1 class='bg-dark-blue'><center> bandeau</center></h1>");
		em.persist(vehic);
		et.commit();
		em.close();

	}

}
