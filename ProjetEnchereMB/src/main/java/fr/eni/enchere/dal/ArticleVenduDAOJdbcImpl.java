package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Utilisateur;

public  class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private final String SELECT_All = "SELECT nomArticle, description, datDebutEncheres, dateFinEncheres, misAPrix, adresse";
	private final String INSERT = "INSERT into ARTICLE_VENDU (nomArticle, description, datDebutEncheres, dateFinEncheres, misAPrix, adresse)VALUES(?,?,?,?,?,?)";
    private final String DELETE = "DELETE FROM ARTICLE_VENDU WHERE noArticle=";
	private final String UPDATE = "UPDATE FROM ARTICLE_VENDU SET nomArticle=?, description=?, dateDebutEncheres=?, dateFinEncheres=?, misAPrix=?, adresse=?";
	
	
	
	@Override
   public List<ArticleVendu> selectAll() {

		try {
	    	Connection cnx = ConnectionProvider.getConnection();
	    	Statement stmt = cnx.createStatement();
	    	ResultSet rs = stmt.executeQuery(SELECT_All);
		
			String nomArticle, description, dateDebutEncheres, dateFinEncheres, misAPrix, adresse;
			ArticleVendu av = null ;
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
			stmt.close();
			return liste;
           
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public void delete(ArticleVendu  articleVendu) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			Statement stmt;
			stmt=cnx.createStatement();
			stmt.executeUpdate(DELETE);
			cnx.close();
			
			
			
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	@Override
     public void insert(ArticleVendu articleVendu) {
	
    try {
		Connection cnx = ConnectionProvider.getConnection();
		 PreparedStatement pstmt;
		 LocalDate date = LocalDate.now();
		 pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		 
		 
		 pstmt.setString(1, articleVendu.getNomArticle());
		 pstmt.setString(2, articleVendu.getDescription());
		 pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
		 pstmt.setDate(4,java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
		 pstmt.setInt(5, articleVendu.getMiseAPrix());
		 pstmt.setString(1, articleVendu.getCategorie().getLibelle());
		
		 
		 pstmt.executeUpdate();
		 pstmt.close();
		 cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
     }
   @Override
    public  void update(ArticleVendu articleVendu) {
    	
    	try {
    	 Connection cnx = ConnectionProvider.getConnection();
		 PreparedStatement pstmt;
		 pstmt = cnx.prepareStatement(UPDATE);
		 
		 pstmt.setString(1, articleVendu.getNomArticle());
		 pstmt.setString(2, articleVendu.getDescription());
		 pstmt.setDate(3,java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
		 pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
		 pstmt.setInt(5, articleVendu.getMiseAPrix());
		 pstmt.setString(6, articleVendu.getCategorie().getLibelle());
		 pstmt.setString(7, articleVendu.getAdresse().getRue());//rajouter le reste de l'adresse+ convertion a faire pour les types
		
		 pstmt.close();
		 cnx.close();
		 
		 
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
        }
	
	
	
}
   

	
	
}

