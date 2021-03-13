package fr.diginamic.menuServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;

public class MaintenanceValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		// TODO Auto-generated method stub
		Date debut;
		Date retour;
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd/MM/yyyy");
		Date aujourdhui = new Date();
		try {
			debut = formater.parse(form.getValue("dateDebut"));
			retour = formater.parse(form.getValue("dateFin"));
			if (debut.before(aujourdhui) == true) {
				console.alert("La date des réparations ne peut être inferieur à la date du jour !");
				return false;
			} else if (retour.before(debut) == true) {
				console.alert("La date de fin de la maintenance ne peut être inferieur à la date du début !");
				return false;
			} else if (retour.before(aujourdhui) == true) {
				console.alert("La date de fin de la maintenance ne peut être inferieur à la date du jour !");
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	

}
