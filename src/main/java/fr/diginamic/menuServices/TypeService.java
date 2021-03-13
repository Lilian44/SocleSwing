package fr.diginamic.menuServices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Adresse;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Permis;
import fr.diginamic.entites.Type;
import fr.diginamic.entites.Vehicule;

public class TypeService extends MenuService {

	@Override
	public void traitement() {
		// TODO Auto-generated method stub

		console.clear();
//		console.print("<a class='btn-blue' href='createType()'> Nouveau Type de véhicule | </a>");
//		console.print("<a class='btn-blue' href='afficherType()'> Afficher les types  | </a>");
		
		
		console.print("<table cellspacing=0>"
				+ "<tr class='bg-green'><td width='150px'>Créer un nouveau type</td><td width='150px'>liste des types de véhicules</td></tr>"

				+ "<tr>" + "  <td width='150px'><a class='btn-blue' href='createType()'> nouveau type </a></td>"
				+ "</td>" + "  <td width='150px'><a class='btn-blue' href='afficherType()'> liste des typess</a></td>"
				+ "</tr>" + "</table>");
	}

	public void createType() {
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Form form = new Form();

		form.addInput(new TextField("Type :", "nomtype"));
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("prix de la journée:", "montant"));
		form.addInput(new TextField("Caution:", "caution"));

		TypeValidator typevalid = new TypeValidator();
		;
		boolean valide = console.input("Demande d'informations", form, typevalid);

		// RÃ©cupÃ©ation des informations saisies
//		if (valide) {
//			console.print("Vous vous <b>appelez</b> ").println(
//					"<span style='color:red'>" + form.getValue("champ2") + " " + form.getValue("champ1") + "</span>");
//			console.println("Date de naissance :" + form.getValue("dateNaissance"));
//			console.println("Voiture sÃ©lectionnÃ©e :" + form.getValue("vehicule"));
//		}

		Type type = new Type(form.getValue("nomtype"), Double.parseDouble(form.getValue("montant")),
				Double.parseDouble(form.getValue("caution")));

		em.persist(type);
		et.commit();
		em.close();

	}

	public void afficherType() {
		console.clear();
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Type> queryType = em.createQuery("SELECT t FROM Type t", Type.class);

		List<Type> types = queryType.getResultList();

//		for (int i = 0; i < clients.size(); i++) {
//			console.print(clients.get(i).toString(), Color.RED);
//		}

		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Types de vehicules</td></tr>";
		for (Type t : types) {
			html += "<tr>" + "  <td><a class='btn-blue' href='modifierType(" + t.getId()
					+ ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>" + "  <td width='150px'>"
					+ t.getNomType() + "</td>" + "</tr>";
		}
		html += "</table>";

		console.print(html);
	}

	public void modifierType(Long id) {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		console.clear();

		Integer newid = id.intValue();
		Type typeModifier = em.find(Type.class, newid);

		Form form = new Form();

		form.addInput(new TextField("Type :", "nomtype"));
		// On ajoute au formulaire 2 champs de type texte.
		form.addInput(new TextField("prix de la journée:", "montant"));
		form.addInput(new TextField("Caution:", "caution"));

		TypeValidator typevalid = new TypeValidator();

		boolean valide = console.input("modification client", form, typevalid);

		typeModifier.setNomType(form.getValue("nomtype"));
		typeModifier.setMontantJour(Double.parseDouble(form.getValue("montant")));
		typeModifier.setCaution(Double.parseDouble(form.getValue("caution")));

		et.commit();
		em.close();

	}
}
