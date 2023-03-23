package fr.eni.enchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.dal.ArticleVenduDAO;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.exceptions.BLLException;

public class ArticleVenduManager {
	
	private static ArticleVenduManager instance;
	private ArticleVenduDAO ArticleVenduDAO;
	
	
	private ArticleVenduManager(ArticleVenduDAO ArticleVenduDAO) {
		this.ArticleVenduDAO = ArticleVenduDAO;
	}

	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager(DAOFactory.getArticleVenduDAO());
		}
		return instance;
	}
	
	
	public List<ArticleVendu> getAllArticleVendu() throws BLLException, SQLException{
		
		List<ArticleVendu> lst= ArticleVenduDAO.selectAll();
		if(lst == null) {
			throw new BLLException ("Liste des articles vendu vide");
		}
		return lst;
		
	}
}
