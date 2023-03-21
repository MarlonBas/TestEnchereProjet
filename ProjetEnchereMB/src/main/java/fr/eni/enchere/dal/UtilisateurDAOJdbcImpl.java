package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.javaee.tpmodule6.dal.ConnectionProvider;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEUR (pseudo,nom,prenom,email,telephone,idAdresse,motDePasse,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,);";
	
	private static final String INSERT_ADRESSE = "INSERT INTO ADRESSE (rue,code_postal,ville) VALUES (?,?,?);";
	
	@Override
	public void insert(Utilisateur utilisateur) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt;
			ResultSet rs;
			pstmt = cnx.prepareStatement(INSERT_ADRESSE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,Utilisateur.getAdresse().getRue());
			pstmt.setString(2,Utilisateur.getAdresse().getCodePostal());
			pstmt.setString(2,Utilisateur.getAdresse().getVille());
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			if(rs.next())
			{
				Utilisateur.getAdresse().setIdAdresse(rs.getInt("idAdresse"));
			}
			rs.close();
			pstmt.close();
			
			pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,Utilisateur.getPseudo());
			pstmt.setString(2,Utilisateur.getNom());
			pstmt.setString(3,Utilisateur.getPrenom());
			pstmt.setString(4,Utilisateur.getEmail());
			pstmt.setString(5,Utilisateur.getTelephone());
			pstmt.setInt(6, Utilisateur.getAdresse().getIdAdresse());
			pstmt.setString(7, Utilisateur.getMotDePasse());
			pstmt.setInt(8, Utilisateur.getCredit());
			pstmt.setBoolean(9, Utilisateur.getAdministrateur());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				Utilisateur.setNoUtilisateur(rs.getInt("noUtilisateur"));
			}
			rs.close();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int noUtilisateur) {
		
	}

	@Override
	public List<Utilisateur> selectAll() {
		return null;
	}

	@Override
	public Utilisateur selectById(int noUtilisateur) {
		return null;
	}

}
