package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bo.Utilisateur;



public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEUR (pseudo,nom,prenom,email,telephone,idAdresse,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,);";
	private static final String INSERT_ADRESSE = "INSERT INTO ADRESSE (rue,code_postal,ville) VALUES (?,?,?);";
	private static final String LOGIN = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,id_adresse,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE email=? and mot_de_passe=?";
	
	@Override
	public void insert(Utilisateur utilisateur) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt;
			ResultSet rs;
			pstmt = cnx.prepareStatement(INSERT_ADRESSE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getAdresse().getRue());
			pstmt.setInt(2, utilisateur.getAdresse().getCodePostal());
			pstmt.setString(2,utilisateur.getAdresse().getVille());
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.getAdresse().setIdAdresse(rs.getInt("idAdresse"));
			}
			rs.close();
			pstmt.close();
			
			pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,utilisateur.getPseudo());
			pstmt.setString(2,utilisateur.getNom());
			pstmt.setString(3,utilisateur.getPrenom());
			pstmt.setString(4,utilisateur.getEmail());
			pstmt.setString(5,utilisateur.getTelephone());
			pstmt.setInt(6, utilisateur.getAdresse().getIdAdresse());
			pstmt.setString(7, utilisateur.getMotDePasse());
			pstmt.setInt(8, utilisateur.getCredit());
			pstmt.setBoolean(9, utilisateur.isAdministrateur());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt("noUtilisateur"));
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

	@Override
	public Utilisateur login(String email, String mot_de_passe) {
		
		Utilisateur utilisateur=null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LOGIN);
			pstmt.setString(1, email);
			pstmt.setString(2, mot_de_passe);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				 utilisateur= rsToUtilisateur(rs);
			}
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return utilisateur;
	}

	
	private Utilisateur rsToUtilisateur(ResultSet rs)
	{
		Utilisateur utilisateur = null;
		try {
			utilisateur =new Utilisateur(rs.getInt("no_utilisateur"),
							  rs.getString("pseudo"),
							  rs.getString("nom"),
							  rs.getString("prenom"),
							  rs.getString("email"),
							  rs.getString("telephone"),
							  AdresseManager.getInstance().findById(rs.getInt("id_adresse")),
							  rs.getString("motDePasse"),
							  rs.getInt("credit"),
							  rs.getBoolean("administrateur")
							  );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return utilisateur;
	}

}
