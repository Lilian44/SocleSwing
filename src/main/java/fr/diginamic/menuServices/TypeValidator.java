package fr.diginamic.menuServices;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;

public class TypeValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		// TODO Auto-generated method stub
		String typeName = form.getValue("nomtype");
		String montant = form.getValue("montant");
		String caution = form.getValue("caution");
		
	

		if (typeName.trim().isEmpty()) {
			console.alert("La type de vehicule est obligatoire !");
			return false;
		} else if (montant.trim().isEmpty()) {
			console.alert("Le montant est obligatoire !");
			return false;
		} else if (caution.trim().isEmpty()) {
			console.alert("La caution est obligatoire !");
			return false;
		} else if (Double.parseDouble(caution.trim()) <0) {
			console.alert("La caution doit être superieur à 0 !");
			return false;
		}
		else if (Double.parseDouble(montant.trim()) <0) {
			console.alert("Le montant journalier doit être superieur à 0 !");
			return false;
		}
		return true;
	}

}
