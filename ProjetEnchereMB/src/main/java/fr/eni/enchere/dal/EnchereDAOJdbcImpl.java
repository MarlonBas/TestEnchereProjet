package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO{
	
	private final String INSERT = "INSERT into ENCHERES (date_enchere, montant_enchere, id_article, no_utilisateur)VALUES(?,?,?,?)";
	private final String DELETE = "DELETE FROM ENCHERES where id_enchere=?";
	
	private static final String SELECT_BY_ID = "SELECT * FROM ENCHERES WHERE id_enchere=?";
	private static final String SELECT_ALL = "SELECT * FROM ENCHERES";
	
	
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
	
	@Override
	public Enchere selectByID(int idCategorie) {
		Enchere enchere = null;
		try {	Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1,idCategorie);
			
			ResultSet rs=pstmt.executeQuery();
			if (rs.next()) {
				enchere = new Enchere(rs.getInt("id_enchere"),
						UtilisateurManager.getInstance().selectById(rs.getInt("no_utilisateur")),
						ArticleVenduManager.getInstance().selectArticleById(rs.getInt("id_article")),
						rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere"));
			}
			cnx.close();		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return enchere;
	}
	
	@Override
	public List<Enchere> selectAll() {
		List<Enchere> encheres = new ArrayList<Enchere>();
		try {	
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				encheres.add(new Enchere(rs.getInt("id_enchere"),
						UtilisateurManager.getInstance().selectById(rs.getInt("no_utilisateur")),
						ArticleVenduManager.getInstance().selectArticleById(rs.getInt("id_article")),
						rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere")));
			}
			cnx.close();		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return encheres;
	}
}    
