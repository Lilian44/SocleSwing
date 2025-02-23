package fr.diginamic.menuServices;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFileChooser;

import fr.diginamic.composants.MenuService;
import fr.diginamic.composants.ui.DateField;
import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.ui.TextField;
import fr.diginamic.entites.Adresse;
import fr.diginamic.entites.Application;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Permis;

import fr.diginamicDaos.ClientDao;

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

	@Override
	public void traitement() {
		// TODO Auto-generated method stub
		console.clear();

		console.print("<table cellspacing=0>"
				+ "<tr class='bg-green'><td width='150px'>Nouveau client</td><td width='150px'>liste des clients</td><td width='150px'>Exporter la liste clients</td></tr>"

				+ "<tr>" + "  <td width='150px'><a class='btn-blue' href='createClient()'> Nouveau client | </a></td>"
				+ "</td>"
				+ "  <td width='150px'><a class='btn-blue' href='afficherClients()'> liste des clients</a></td>"
				+ "  <td width='150px'><a class='btn-blue' href='exportClient()'> exporter liste des clients</a></td>"
				+ "</tr>" + "</table>");

	}

	public void afficherClients() {
		console.clear();

		ClientDao allClient = new ClientDao();
		List<Client> clients = allClient.allClients();

		String html = "<table cellspacing=0>"
				+ "<tr class='bg-green'><td>&nbsp;</td><td>&nbsp;</td><td>Nom</td><td>Prénom</td></tr>";
		for (Client c : clients) {
			html += "<tr>" + "  <td><a class='btn-blue' href='modifierClient(" + c.getId()
					+ ")'><img width=25 src='images/pencil-blue-xs.png'></a></td>"
					+ "  <td><a class='btn-red' href='supprimer(" + c.getId()
					+ ")'><img width=25 src='images/trash-red-xs.png'></a></td>" + "  <td width='150px'>" + c.getNom()
					+ "</td>" + "  <td width='150px'>" + c.getPrenom() + "</td>" + "</tr>";
		}
		html += "</table>";

		console.print(html);

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
		form.addInput(new DateField("date d'obtention du permis", "datePermis"));
		form.addInput(new TextField("numero de permis:", "numeroPermis"));
		form.addInput(new TextField("type:", "typePermis"));

		// Récupéation des informations saisies
//		if (valide) {
//			console.print("Vous vous <b>appelez</b> ").println(
//					"<span style='color:red'>" + form.getValue("champ2") + " " + form.getValue("champ1") + "</span>");
//			console.println("Date de naissance :" + form.getValue("dateNaissance"));
//			console.println("Voiture sélectionnée :" + form.getValue("vehicule"));
//		}

		ClientValidator validator = new ClientValidator();
		boolean valide = console.input("Nouveau client", form, validator);

		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date datePermis;

		try {
			datePermis = sourceFormat.parse(form.getValue("datePermis"));

			Permis permis = new Permis(form.getValue("typePermis"), Integer.parseInt(form.getValue("numeroPermis")),
					datePermis);
			Adresse adresse = new Adresse(form.getValue("numero"), form.getValue("libelle"),
					form.getValue("codePostal"), form.getValue("ville"), Integer.parseInt(form.getValue("telephone")),
					form.getValue("mail"));

			Client client = new Client(form.getValue("nom"), form.getValue("prenom"), adresse, permis);

			em.persist(client);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		et.commit();
		em.close();

	}

	public void modifierClient(Long id) {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		console.clear();

		Integer newid = id.intValue();
		Client clientModifier = em.find(Client.class, newid);

		Form form = new Form();

		form.addInput(new TextField("Ville :", "ville", clientModifier.getAdresse().getVille()));
		form.addInput(new TextField("code postal:", "codePostal", clientModifier.getAdresse().getCodePostal()));
		form.addInput(new TextField("libelle:", "libelle", clientModifier.getAdresse().getLibelle()));
		form.addInput(new TextField("numero:", "numero", clientModifier.getAdresse().getNumeroRue()));
		form.addInput(new TextField("mail:", "mail", clientModifier.getAdresse().getMail()));
		form.addInput(new TextField("telephone:", "telephone", "0"+String.valueOf(clientModifier.getAdresse().getTelephone())));
		form.addInput(new TextField("nom:", "nom", clientModifier.getNom()));
		form.addInput(new TextField("prenom:", "prenom", clientModifier.getPrenom()));
		form.addInput(new DateField("date d'obtention du permis", "datePermis"));
		form.addInput(new TextField("numero de permis:", "numeroPermis"));
		form.addInput(new TextField("type:", "typePermis", clientModifier.getPermis().getType()));

		
		ClientValidator validator = new ClientValidator();
		boolean valide = console.input("modification client", form, validator);
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date datePermis;
		try {
			datePermis = sourceFormat.parse(form.getValue("datePermis"));
			Permis newpermis = new Permis(form.getValue("typePermis"), Integer.parseInt(form.getValue("numeroPermis")),
					datePermis);

			Adresse newAdresse = new Adresse(form.getValue("numero"), form.getValue("libelle"),
					form.getValue("codePostal"), form.getValue("ville"), Integer.parseInt(form.getValue("telephone")),
					form.getValue("mail"));
			clientModifier.setAdresse(newAdresse);
			clientModifier.setPrenom(form.getValue("prenom"));
			clientModifier.setNom(form.getValue("nom"));
			clientModifier.setPermis(newpermis);

			et.commit();
			em.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		traitement();

	}

	public void supprimer(Long id) {

		EntityManager em = Application.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		console.clear();

		Integer newid = id.intValue();
		ClientDao delete = new ClientDao();
		delete.deleteClients(newid);
		et.commit();
		em.close();
	}

	public void exportClient() {

		FileWriter fileWriter;
		try {

			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int ret = jfc.showOpenDialog(null);

			ClientDao allClient = new ClientDao();
			List<Client> clients = allClient.allClients();
			fileWriter = new FileWriter(jfc.getSelectedFile() + "/Clients.csv");
			fileWriter.append("Nom; Prenom ; code postal ; Ville ; Numero ; Libelle \n");
			for (Client c : clients) {

				fileWriter.append(c.getNom() + ";" + c.getPrenom() + ";" + c.getAdresse().getCodePostal() + ";"
						+ c.getAdresse().getVille() + ";" + c.getAdresse().getNumeroRue() + ";"
						+ c.getAdresse().getLibelle() + "\n");

			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
