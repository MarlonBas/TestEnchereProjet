package fr.eni.enchere.dal;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.ArticleVendu;

public  class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private final String SELECT_All = "SELECT * FROM articles ";
	private final String SELECT_BY_ID = "SELECT * FROM articles WHERE id_article=?;";
	private final String INSERT = "INSERT into ARTICLES (no_utilisateur, id_adresse, id_categorie, nom_article, descr_article, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente )VALUES(?,?,?,?,?,?,?,?,?,?)";
    private final String DELETE = "DELETE FROM ARTICLES WHERE id_article=?";
	private final String UPDATE = "UPDATE ARTICLES SET no_utilisateur=?, id_adresse=?, id_categorie=?, nom_article=?, descr_article=?, date_debut_encheres=?, date_fin_encheres=?, mise_a_prix=?, prix_vente=?, etat_vente=? WHERE id_article=?";
	
	
	
	@Override
   public List<ArticleVendu> selectAll() {

		try {
	    	Connection cnx = ConnectionProvider.getConnection();
	    	Statement stmt = cnx.createStatement();
	    	ResultSet rs = stmt.executeQuery(SELECT_All);
	   	    int no_utilisateur, id_adresse,id_article, id_categorie, prix_vente ,mise_a_prix;
			String nom_article, descr_article,  etat_vente;
			LocalDate date_debut_encheres, date_fin_encheres;
			ArticleVendu av = null ;
			List<ArticleVendu> liste = new ArrayList<>();
			
			while(rs.next()) {
				id_article = rs.getInt("id_article");
				no_utilisateur = rs.getInt("no_utilisateur");
				id_adresse = rs.getInt("id_adresse");
				id_categorie = rs.getInt("id_categorie");
				nom_article = rs.getString("nom_article");
				descr_article = rs.getString("descr_article");
				date_debut_encheres = rs.getDate("date_debut_encheres").toLocalDate();
				date_fin_encheres = rs.getDate("date_fin_encheres").toLocalDate();
				mise_a_prix = rs.getInt("mise_a_prix");
				prix_vente = rs.getInt("prix_vente");
				etat_vente = rs.getString("etat_vente");
				
				av = new ArticleVendu(id_article, nom_article, descr_article, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente, AdresseManager.getInstance().findById(id_adresse), UtilisateurManager.getInstance().selectById(no_utilisateur), CategorieManager.getInstance().selectCategorie(id_categorie) );
				
				// Mettre à jour l'état de l'article s'il n'est pas à jour
				if (LocalDate.now().isBefore(date_debut_encheres)) {
					if (!etat_vente.equals("pas debute")) {
						av.setEtatVente("pas debute");
						ArticleVenduManager.getInstance().modifierArticleVendu(av);
					}
				}
				if (LocalDate.now().isBefore(date_fin_encheres) && LocalDate.now().isAfter(date_debut_encheres)) {
					if (!etat_vente.equals("en cours")) {
						av.setEtatVente("en cours");
						ArticleVenduManager.getInstance().modifierArticleVendu(av);
					}
				}
				if (LocalDate.now().isAfter(date_fin_encheres)) {
					if (!etat_vente.equals("termine")) {
						av.setEtatVente("termine");
						ArticleVenduManager.getInstance().modifierArticleVendu(av);
					}
				}
				
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
	public ArticleVendu selectById(int id_article) {
		ArticleVendu article = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt;
			
			
			pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id_article);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new ArticleVendu(
						rs.getInt("id_article"),
						rs.getString("nom_article"),
						rs.getString("descr_article"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("mise_a_prix"),
						rs.getInt("prix_vente"),
						rs.getString("etat_vente"),
						AdresseManager.getInstance().findById(rs.getInt("id_adresse")),
						UtilisateurManager.getInstance().selectById(rs.getInt("no_utilisateur")),
						CategorieManager.getInstance().selectCategorie(rs.getInt("id_categorie")));
				
				// Mettre à jour l'état de l'article s'il n'est pas à jour
				if (LocalDate.now().isBefore(rs.getDate("date_debut_encheres").toLocalDate())) {
					if (!rs.getString("etat_vente").equals("pas debute")) {
						article.setEtatVente("pas debute");
						ArticleVenduManager.getInstance().modifierArticleVendu(article);
					}
				}
				if (LocalDate.now().isBefore(rs.getDate("date_fin_encheres").toLocalDate()) && LocalDate.now().isAfter(rs.getDate("date_debut_encheres").toLocalDate())) {
					if (!rs.getString("etat_vente").equals("en cours")) {
						article.setEtatVente("en cours");
						ArticleVenduManager.getInstance().modifierArticleVendu(article);
					}
				}
				if (LocalDate.now().isAfter(rs.getDate("date_fin_encheres").toLocalDate())) {
					if (!rs.getString("etat_vente").equals("termine")) {
						article.setEtatVente("termine");
						ArticleVenduManager.getInstance().modifierArticleVendu(article);
					}
				}
			
			} else {
				article=null;
			}
			pstmt.close();
			rs.close();
			cnx.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return article;
	}
	   
	
	@Override
	public void delete(ArticleVendu  articles) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt;
			stmt= cnx.prepareStatement(DELETE);
			stmt.setInt(1, articles.getNoArticle());
			
			stmt.executeUpdate();
			cnx.close();
			
			AdresseManager.getInstance().delete(articles.getAdresse());//supprimer l'adresse associer a l'article supprimé
			
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	@Override
     public ArticleVendu insert(ArticleVendu articles) {
	
    try {
		Connection cnx = ConnectionProvider.getConnection();
		 PreparedStatement pstmt;
		 ResultSet rs;
		 Adresse nouvelleAdresseID = AdresseManager.getInstance().insert(articles.getAdresse());
		 articles.setAdresse(nouvelleAdresseID);
		 pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		 
		 pstmt.setInt(1,articles.getUtilisateur().getNoUtilisateur());
		 pstmt.setInt(2,articles.getAdresse().getId_adresse());
		 pstmt.setInt(3, articles.getCategorie().getNoCategorie());
		 pstmt.setString(4, articles.getNomArticle());
		 pstmt.setString(5, articles.getDescription());
		 pstmt.setDate(6, java.sql.Date.valueOf(articles.getDateDebutEncheres()));
		 pstmt.setDate(7,java.sql.Date.valueOf(articles.getDateFinEncheres()));
		 pstmt.setInt(8, articles.getMiseAPrix());
		 pstmt.setInt(9, articles.getPrixVente());
		 pstmt.setString(10, articles.getEtatVente());
	
		 pstmt.executeUpdate();
		 rs = pstmt.getGeneratedKeys();
			if(rs.next()) 
				articles.setNoArticle(rs.getInt(1));
		 rs.close();
		 pstmt.close();
		 cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return articles; 
     }
   @Override
    public  void update(ArticleVendu articles) {
    	
    	try {
    	 Connection cnx = ConnectionProvider.getConnection();
		 PreparedStatement pstmt;
		 AdresseManager.getInstance().update(articles.getAdresse());
		
		 pstmt = cnx.prepareStatement(UPDATE);
		 
		 pstmt.setInt(1,articles.getUtilisateur().getNoUtilisateur());
		 pstmt.setInt(2,articles.getAdresse().getId_adresse());
		 pstmt.setInt(3, articles.getCategorie().getNoCategorie());
		 pstmt.setString(4, articles.getNomArticle());
		 pstmt.setString(5, articles.getDescription());
		 pstmt.setDate(6, java.sql.Date.valueOf(articles.getDateDebutEncheres()));
		 pstmt.setDate(7,java.sql.Date.valueOf(articles.getDateFinEncheres()));
		 pstmt.setInt(8, articles.getMiseAPrix());
		 pstmt.setInt(9, articles.getPrixVente());
		 pstmt.setString(10, articles.getEtatVente());
		 pstmt.setInt(11, articles.getNoArticle());
		 
		 pstmt.executeUpdate();
		 pstmt.close();
		 cnx.close();
		 
		 
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
        }
	
	
	
}



	
	
}

