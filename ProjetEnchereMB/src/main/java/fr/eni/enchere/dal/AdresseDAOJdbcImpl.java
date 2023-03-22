package fr.eni.enchere.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.enchere.bo.Adresse;

public class AdresseDAOJdbcImpl implements AdresseDAO{

	private static final String SELECT_ADRESSE_BY_NO_UTILISATEUR = "SELECT id_adresse, no_utilisateur, rue, code_postal, ville FROM ADRESSES WHERE no_utilisateur=?;";
	
	@Override
	public Adresse selectById(int no_utilisateur) {
		Adresse adresse=null;
		try {
		ResultSet rs;
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cnx.prepareStatement(SELECT_ADRESSE_BY_NO_UTILISATEUR);
		pstmt.setInt(1, no_utilisateur);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			adresse= new Adresse(rs.getInt("id_adresse"),rs.getInt("no_utilisateur"),rs.getString("rue"),rs.getInt("code_postal"),rs.getString("ville"));
		}
		pstmt.close();
		cnx.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return adresse;
	}

}
