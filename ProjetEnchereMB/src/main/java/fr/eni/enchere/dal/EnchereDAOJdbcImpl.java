package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import fr.eni.enchere.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO{
	
	private final String INSERT = "INSERT into ENCHERES (date_enchere, montant_enchere, id_article, no_utilisateur)VALUES(?,?,?,?)";
	private final String DELETE = "DELETE FROM ENCHERES where id_enchere=?";
	
	
	@Override
	public   void insert ( Enchere enchere) {
	try {
	 Connection cnx = ConnectionProvider.getConnection();
	 PreparedStatement pstmt;
	 LocalDate date = LocalDate.now();
	 pstmt = cnx.prepareStatement(INSERT);
	 
	
	 pstmt.setDate(3,java.sql.Date.valueOf(enchere.getDateEnchere()));
	 pstmt.setInt(4, enchere.getMontantEnchere());
	 pstmt.setInt(1,enchere.getUtilisateur().getNoUtilisateur());
	 pstmt.setString(2,enchere.getArticleVendu().getNomArticle());
	 
	 pstmt.executeUpdate();
	 pstmt.close();
	 cnx.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	@Override
    public void delete (int Encheres) {
    	
    
    	Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	{try {
			PreparedStatement stmt;
			stmt= cnx.prepareStatement(DELETE);
			
				stmt.setInt(1, Encheres);
			
    	
			stmt.executeUpdate();
			stmt.close();
			cnx.close();
    	}catch (Exception e) {
			
		}
	  } 
    	
	}
}    
