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
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}

	public static EnchereDAO getEnchereDAO() {
		
		return new  EnchereDAOJdbcImpl();
	}

	
}
