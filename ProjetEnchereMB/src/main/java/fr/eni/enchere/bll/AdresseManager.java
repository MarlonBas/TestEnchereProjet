package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.dal.AdresseDAO;
import fr.eni.enchere.dal.DAOFactory;

public class AdresseManager {
	
	private static AdresseManager instance = null;
	private AdresseDAO adresseDAO;
	
	private AdresseManager (AdresseDAO adresseDAO) {
		this.adresseDAO = adresseDAO;
	}

	//SINGLETON
	public static AdresseManager getInstance() {
		if(instance==null) {
			instance = new AdresseManager(DAOFactory.getAdresseDAO());
		}
		return instance;
	}
	
	public Adresse findById(int no_utilisateur) {
		return adresseDAO.selectById(no_utilisateur);
	}
	
}
