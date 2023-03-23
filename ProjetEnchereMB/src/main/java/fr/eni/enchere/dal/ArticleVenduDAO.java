package fr.eni.enchere.dal;

import java.sql.SQLException;
import java.util.List;


import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.exceptions.DALException;

public interface ArticleVenduDAO {
	
	List<ArticleVendu> selectAll() throws  SQLException;

	
}
