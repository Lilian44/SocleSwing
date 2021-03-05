package fr.diginamic.menuServices;

import fr.diginamic.composants.ui.Form;
import fr.diginamic.composants.validator.FormValidator;

public class ClientValidator extends FormValidator {

	@Override
	public boolean validate(Form form) {
		// TODO Auto-generated method stub
		String nvVille = form.getValue("ville");
		String nvCodePostal = form.getValue("codePostal");
		String nvLibelle = form.getValue("libelle");
		String nvNumero = form.getValue("numero");
		String nvMail = form.getValue("mail");
		String nvTelephone = form.getValue("telephone");
		String nvNom = form.getValue("nom");
		String nvPrenom = form.getValue("prenom");

		if (nvVille.trim().isEmpty()) {
			console.alert("La ville est obligatoire !");
			return false;
		} else if (nvCodePostal.trim().isEmpty()) {
			console.alert("Le code postal est obligatoire !");
			return false;
		} else if (nvLibelle.trim().isEmpty()) {
			console.alert("Le libellé est obligatoire !");
			return false;
		} else if (nvNumero.trim().isEmpty()) {
			console.alert("Le numéro est obligatoire !");
			return false;
		} else if (nvMail.trim().isEmpty()) {
			console.alert("Le mail est obligatoire !");
			return false;
		} else if (nvTelephone.trim().isEmpty()) {
			console.alert("Le téléphone est obligatoire !");
			return false;
		} else if (nvNom.trim().isEmpty()) {
			console.alert("Le nom est obligatoire !");
			return false;
		} else if (nvPrenom.trim().isEmpty()) {
			console.alert("Le prÃ©nom est obligatoire !");
			return false;
		} else if (nvTelephone.length() > 10
				|| nvTelephone.length() < 10 || Integer.parseInt(nvTelephone.substring(1, 2)) < 1) {
			console.alert("Le format du numéro de téléphone est incorrect  !");
			return false;
		} 
			else if (nvMail != "") {
//			Boolean mail = false;
//			Boolean mail2 = false;
//			for (int i = 0; i < nvMail.length(); i++) {
//				if (nvMail.charAt(i) == "@") {
//					
//				}
//			}
//
//			console.alert("Le format du mail est incorrect  !");
//			return false;
		}

		return true;
	}

}
