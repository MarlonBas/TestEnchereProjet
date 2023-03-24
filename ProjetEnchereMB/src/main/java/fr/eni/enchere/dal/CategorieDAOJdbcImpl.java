package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE id_categorie=?";
	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES";
	
	@Override
	public Categorie selectByID(int noCategorie) {
		Categorie categorie = null;
		try {	Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1,noCategorie);
			
			ResultSet rs=pstmt.executeQuery();
			if (rs.next()) {
				categorie = new Categorie(rs.getInt("id"),rs.getString("nom"));
			}
			cnx.close();		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return categorie;
	}
	
	@Override
	public List<Categorie> selectAll() {
		List<Categorie> categories = new ArrayList<Categorie>();
		try {	Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				categories.add(new Categorie(rs.getInt("id"),rs.getString("nom")));
			}
			cnx.close();		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
}
