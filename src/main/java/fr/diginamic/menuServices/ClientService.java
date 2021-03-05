package fr.diginamic.menuServices;

import java.awt.Color;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Adresse;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;

public class ClientService extends MenuService {

	private EntityManager em;

	public ClientService(EntityManager em) {
	}

	public Client findById(int id) {

		return em.find(Client.class, id);
	}

	public void insert(Client client) {

		em.persist(client);

	}

	public ClientService() {
	}

//	public void update(Client client) {
//		Client clientDB = findById(client.getId());
//		clientDB.setNom(client.getNom());
//	}
//
//	public void delete(Client client) {
//		Client clietnDb = findById(client.getId());
//		em.refresh(clietnDb);
//
//	}

	@Override
	public void traitement() {
		// TODO Auto-generated method stub
		console.clear();
		console.print("<a class='btn-blue' href='createClient()'> Nouveau client</a>");
		console.print("<a class='btn-blue' href='afficherClients()'> liste des c</a>");

	}

	public void afficherClients() {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		TypedQuery<Client> queryClients = em.createQuery("SELECT c FROM Client v", Client.class);

		List<Client> clients = queryClients.getResultList();

		for (int i = 0; i < clients.size(); i++) {
			console.print(clients.get(i).toString(), Color.RED);
		}

	}

	public void createClient() {
		
		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Form form = new Form();

		form.addInput(new TextField("Ville :", "ville"));
		form.addInput(new TextField("code postal:", "codePostal"));
		form.addInput(new TextField("libelle:", "libelle"));
		form.addInput(new TextField("numero:", "numero"));
		form.addInput(new TextField("mail:", "mail"));
		form.addInput(new TextField("telephone:", "telephone"));
		form.addInput(new TextField("nom:", "nom"));
		form.addInput(new TextField("prenom:", "prenom"));

		ClientValidator validator = new ClientValidator();
		boolean valide = console.input("Demande d'informations", form, validator);

		// Récupéation des informations saisies
//		if (valide) {
//			console.print("Vous vous <b>appelez</b> ").println(
//					"<span style='color:red'>" + form.getValue("champ2") + " " + form.getValue("champ1") + "</span>");
//			console.println("Date de naissance :" + form.getValue("dateNaissance"));
//			console.println("Voiture sélectionnée :" + form.getValue("vehicule"));
//		}

		Adresse adresse = new Adresse(form.getValue("numero"), form.getValue("libelle"), form.getValue("codePostal"),
				form.getValue("ville"), Integer.parseInt(form.getValue("telephone")), form.getValue("telephone"));

		Client client = new Client(form.getValue("nom"), form.getValue("prenom"), adresse);
		console.print("<h1 class='bg-dark-blue'><center> bandeau</center></h1>");
		em.persist(client);
		et.commit();
		em.close();

	}

}
