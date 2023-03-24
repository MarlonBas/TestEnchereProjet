package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DAOFactory;

public class CategorieManager {
		// LE SINGLETON
	
		private static CategorieManager instance = null;
		private CategorieDAO categorieDAO;
		
		private CategorieManager(CategorieDAO categorieDAO) {
			this.categorieDAO = categorieDAO;
		}
		
		public static CategorieManager getInstance() {
			if (instance == null) {
				instance = new CategorieManager(DAOFactory.getCategorieDAO());
			}
			return instance;
		}
		
		// LES FONCTIONS DE GESTION DES CATEGORIES
		
		public Categorie selectCategorie(int noCategorie) {
			return categorieDAO.selectByID(noCategorie);
		}
		
		public List<Categorie> selectAllCategories() {
			return categorieDAO.selectAll();
		}

}
