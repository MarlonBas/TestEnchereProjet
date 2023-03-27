package fr.eni.enchere.bll;

import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.EnchereDAO;

public class EnchereManager {
	
	private static EnchereManager instance = null;
	private EnchereDAO EnchereDAO;

	private EnchereManager(EnchereDAO enchereDAO) {              
		this.EnchereDAO = enchereDAO;                              
	}                                                                  
	
	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager(DAOFactory.getEnchereDAO());
		}
		return instance;
	}
}
