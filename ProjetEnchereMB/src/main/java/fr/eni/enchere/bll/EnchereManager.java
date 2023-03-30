package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
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
	
	public Enchere selectEnchere(int idEnchere) {
		return EnchereDAO.selectByID(idEnchere);
	}
	
	public List<Enchere> selectAllEncheres() {
		return EnchereDAO.selectAll();
	}
	
	public void insertEnchere(Enchere enchere) {
		EnchereDAO.insert(enchere);
	}
	
	public void deleteEnchere(int idEnchere) {
		EnchereDAO.delete(idEnchere);
	}

}
