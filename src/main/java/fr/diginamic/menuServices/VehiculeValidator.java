package fr.diginamic.menuServices;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;
import fr.diginamic.entites.Type;

public class VehiculeValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		// TODO Auto-generated method stub

		String immat = form.getValue("immatriculation");
		String kilometrage = form.getValue("kilometrage");
		String places = form.getValue("nbrPlaces");
	

		if (immat.trim().isEmpty()) {
			console.alert("La plaque d'immatriculation est obligatoire !");
			return false;
		} else if (kilometrage.trim().isEmpty()) {
			console.alert("Le kilometrage est obligatoire !");
			return false;
		} else if (places.trim().isEmpty()) {
			console.alert("Le nombre de places est obligatoire !");
			return false;
		} else if (immat.matches("^[A-Z]{2}[-][0-9]{3}[-][A-Z]{2}$") == false) {
			console.alert("Le format de plaque attendu est : AB-123-AB  !");
			return false;
		}
		return true;
	}

}
