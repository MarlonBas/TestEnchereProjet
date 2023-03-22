package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bo.Utilisateur;



public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEUR (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?);";
	private static final String INSERT_ADRESSE = "INSERT INTO ADRESSE (no_utilisateur,rue,code_postal,ville) VALUES (?,?,?,?);";
	private static final String LOGIN = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE pseudo=? and mot_de_passe=?";
	private static final String LOGIN_EMAIL = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE email=? and mot_de_passe=?";
	
	@Override
	public void insert(Utilisateur utilisateur) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt;
			ResultSet rs;
			
			//Insertion de l'utilisateur et récupération de son numéro
			pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,utilisateur.getPseudo());
			pstmt.setString(2,utilisateur.getNom());
			pstmt.setString(3,utilisateur.getPrenom());
			pstmt.setString(4,utilisateur.getEmail());
			pstmt.setString(5,utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getMotDePasse());
			pstmt.setInt(7, 100);
			pstmt.setBoolean(8, false);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			}
			rs.close();
			pstmt.close();
			
			//Insertion de l'adresse de l'utilisateur dans la table adresse
			pstmt = cnx.prepareStatement(INSERT_ADRESSE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,utilisateur.getNoUtilisateur());
			pstmt.setString(1, utilisateur.getAdresse().getRue());
			pstmt.setInt(2, utilisateur.getAdresse().getCodePostal());
			pstmt.setString(2,utilisateur.getAdresse().getVille());
			pstmt.executeUpdate();
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
	public Utilisateur login(String pseudo, String mot_de_passe) {
		
		Utilisateur utilisateur=null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LOGIN);
			pstmt.setString(1, pseudo);
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
	
	@Override
	public Utilisateur loginEmail(String email, String mot_de_passe) {
		
		Utilisateur utilisateur=null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LOGIN_EMAIL);
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
							  AdresseManager.getInstance().findById(rs.getInt("no_utilisateur")),
							  rs.getString("mot_de_passe"),
							  rs.getInt("credit"),
							  rs.getBoolean("administrateur")
							  );
			;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return utilisateur;
	}

}
