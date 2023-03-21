package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.enchere.bo.Adresse;

public class AdresseDAOJdbcImpl implements AdresseDAO{

	private static final String SELECT_ADRESSE_BY_ID = "SELECT FROM ADRESSE (id_adresse,rue,code_postal,ville) WHERE id_adresse=?;";
	
	@Override
	public Adresse selectById(int id_Adresse) {
		Adresse adresse=null;
		try {
		ResultSet rs;
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cnx.prepareStatement(SELECT_ADRESSE_BY_ID);
		pstmt.setInt(1, id_Adresse);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			adresse= new Adresse(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return adresse;
	}

}
