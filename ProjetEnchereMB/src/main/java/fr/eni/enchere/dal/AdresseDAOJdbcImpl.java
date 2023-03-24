package fr.eni.enchere.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.enchere.bo.Adresse;

public class AdresseDAOJdbcImpl implements AdresseDAO{

	private static final String SELECT_ADRESSE_BY_ID = "SELECT id_adresse, rue, code_postal, ville FROM ADRESSES WHERE id_adresse=?;";
	private static final String INSERT_ADRESSE = "INSERT INTO ADRESSES (rue,code_postal,ville) VALUES (?,?,?);";
	private static final String UPDATE_ADRESSE = "UPDATE ADRESSE SET rue=?,code_postal=?,ville=? WHERE id_adresse=?;";
	private static final String DELETE_ADRESSE = "DELETE FROM ADRESSES WHERE id_adresse=?";
	
	
	@Override
	public Adresse selectById(int id_adresse) {
		Adresse adresse=null;
		try {
		ResultSet rs;
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cnx.prepareStatement(SELECT_ADRESSE_BY_ID);
		pstmt.setInt(1, id_adresse);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			adresse= new Adresse(rs.getInt("id_adresse"),rs.getString("rue"),rs.getInt("code_postal"),rs.getString("ville"));
		}
		pstmt.close();
		cnx.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return adresse;
	}

	@Override
	public Adresse insert(Adresse adresse) {
		try {
			ResultSet rs;
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ADRESSE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, adresse.getRue());
			pstmt.setInt(2, adresse.getCodePostal());
			pstmt.setString(3, adresse.getVille());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) 
				adresse.setId_adresse(rs.getInt(1));
			rs.close();
			pstmt.close();
			cnx.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return adresse;
	}

	@Override
	public void update(Adresse adresse) {
		try {	
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ADRESSE);
		
		pstmt.setString(1, adresse.getRue());
		pstmt.setInt(2, adresse.getCodePostal());
		pstmt.setString(3, adresse.getVille());
		pstmt.setInt(4, adresse.getId_adresse());
		pstmt.executeUpdate();
		pstmt.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Adresse adresse) {
		try {	
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ADRESSE);
			pstmt.setInt(1, adresse.getId_adresse());
			pstmt.executeUpdate();
			pstmt.close();
			cnx.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
