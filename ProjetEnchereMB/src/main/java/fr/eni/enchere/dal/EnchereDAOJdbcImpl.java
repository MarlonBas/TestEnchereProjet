package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import fr.eni.enchere.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO{
	
	private final String INSERT = "INSERT into ENCHERES (date_enchere, montant_enchere )VALUES(?,?)";
	
	
	
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
}