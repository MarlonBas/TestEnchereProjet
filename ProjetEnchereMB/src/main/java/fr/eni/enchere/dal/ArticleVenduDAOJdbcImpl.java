package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private final String SELECT_All = "SELECT (nomArticle, description, datDebutEncheres, dateFinEncheres, misAPrix, adresse) ";
	private final String INSERT = "INSERT (nomArticle, description, datDebutEncheres, dateFinEncheres, misAPrix, adresse)VALUES(?,?,?,?,?,?);";
    private final String DELETE = "DELETE FROM ARTICLEVENDU WHERE noArticle=";
	private final String UPDATE = "UPDATE FROM ARTICLEVENDU SET nomArticle=?, description=?, dateDebutEncheres=?, dateFinEncheres=?, misAPrix=?, adresse=?,";
	
	
	
	@Override
   public List<ArticleVendu> selectAll() throws SQLException {

	    	Connection cnx = ConnectionProvider.getConnection();
	    	Statement stmt = cnx.createStatement();
	    	ResultSet rs = stmt.executeQuery(SELECT_All);
		
			String nomArticle, description, dateDebutEncheres, dateFinEncheres, misAPrix, adresse;
			ArticleVendu av = null;
			List<ArticleVendu> liste = new ArrayList<>();
			
			while(rs.next()) {
				nomArticle = rs.getString("Nom_Article");
				description = rs.getString("Description");
				dateDebutEncheres = rs.getString("Date_Debut_Encheres");
				dateFinEncheres = rs.getString("Date_Fin_Encheres");
				misAPrix = rs.getString("Mis_a_Prix");
				adresse = rs.getString("Adresse");
				
				liste.add(av);
			 }
			return liste;
           }
}      


