package fr.eni.enchere.dal;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static AdresseDAO getAdresseDAO() {
		return new AdresseDAOJdbcImpl();
	}
	
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		
		return new ArticleVenduDAOJdbcImpl();
	}
}

