package fr.diginamic.menuServices;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Type;
import fr.diginamic.entites.Vehicule;

public class TypeService extends MenuService {

	@Override
	public void traitement() {
		// TODO Auto-generated method stub

		console.clear();
		console.print("<a class='btn-blue' href='createType()'> Nouveau Type de v�hicule</a>");

	}

	public void createType() {
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Form form = new Form();

		form.addInput(new TextField("Type :", "nomtype"));
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("prix de la journ�e:", "montant"));
		form.addInput(new TextField("Caution:", "caution"));

		boolean valide = console.input("Demande d'informations", form, null);

		// Récupéation des informations saisies
//		if (valide) {
//			console.print("Vous vous <b>appelez</b> ").println(
//					"<span style='color:red'>" + form.getValue("champ2") + " " + form.getValue("champ1") + "</span>");
//			console.println("Date de naissance :" + form.getValue("dateNaissance"));
//			console.println("Voiture sélectionnée :" + form.getValue("vehicule"));
//		}

		Type type = new Type(form.getValue("nomtype"), Double.parseDouble(form.getValue("montant")),
				Double.parseDouble(form.getValue("caution")));

		em.persist(type);
		et.commit();
		em.close();

	}
}
