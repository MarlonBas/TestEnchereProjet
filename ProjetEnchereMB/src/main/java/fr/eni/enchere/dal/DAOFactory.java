package fr.eni.enchere.dal;

import fr.eni.enchere.dal.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static AdresseDAO getAdresseDAO() {
		return new AdresseDAOJdbcImpl();
	}
}
