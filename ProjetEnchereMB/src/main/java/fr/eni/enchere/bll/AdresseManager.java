package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.dal.AdresseDAO;
import fr.eni.enchere.dal.AdresseDAOJdbcImpl;

public class AdresseManager {
	
	private static AdresseManager instance = null;
	private AdresseDAO adresseDAO;
	
	private AdresseManager (AdresseDAO adresseDAO) {
		this.adresseDAO = adresseDAO;
	}

	//SINGLETON
	public static AdresseManager getInstance() {
		if(instance==null) {
			instance = new AdresseManager(new AdresseDAOJdbcImpl());
		}
		return instance;
	}
	
	public Adresse findById(int id_adresse) {
		return adresseDAO.selectById(id_adresse);
	}
	
}
