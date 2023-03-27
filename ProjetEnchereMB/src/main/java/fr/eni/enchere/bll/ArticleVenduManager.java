package fr.eni.enchere.bll;

import java.sql.SQLException;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.dal.ArticleVenduDAO;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.exceptions.BllException;

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
	
	
	public List<ArticleVendu> getAllArticleVendu() throws BllException, SQLException{
		
		List<ArticleVendu> articles= ArticleVenduDAO.selectAll();
		if(articles == null) {
			throw new BllException ("Liste des articles vendu vide");
		}
		return articles;
	
	}
	
	public void creerArticleVendu(ArticleVendu article) {
		ArticleVenduDAO.insert(article);
	}
	
	//Corriger delete dans le DAO
	public void supprimerArticleVendu(ArticleVendu article) {
		ArticleVenduDAO.delete(article);
	}
	
	public void modifierArticleVendu(ArticleVendu article) {
		ArticleVenduDAO.update(article);
	}
}

