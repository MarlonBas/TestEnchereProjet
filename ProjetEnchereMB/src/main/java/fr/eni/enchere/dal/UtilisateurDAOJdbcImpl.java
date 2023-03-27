package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;



public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,id_adresse) VALUES (?,?,?,?,?,?,?,?,?);";
	private static final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET nom=?, prenom=?, email=?, telephone=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String SELECT_UTILISATEUR_BY_ID = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,id_adresse FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	private static final String LOGIN_PSEUDO = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,id_adresse FROM UTILISATEURS WHERE pseudo=? and mot_de_passe=?";
	private static final String LOGIN_EMAIL = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,id_adresse FROM UTILISATEURS WHERE email=? and mot_de_passe=?";
	private final static String VERIF_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
	private static final String VERIF_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	
	@Override
	public void insert(Utilisateur utilisateur) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt;
			
			
			//Insertion de l'adresse de l'utilisateur dans la base de données
			Adresse nouvelleAdresseAvecId = AdresseManager.getInstance().insert(utilisateur.getAdresse());
			utilisateur.setAdresse(nouvelleAdresseAvecId);
			
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
			pstmt.setInt(9, utilisateur.getAdresse().getId_adresse());
			pstmt.executeUpdate();
			pstmt.close();
			cnx.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Utilisateur utilisateur)
	{
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt;
			
			//Mise à jour de l'utilisateur
			pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			pstmt.setString(1,utilisateur.getNom());
			pstmt.setString(2,utilisateur.getPrenom());
			pstmt.setString(3,utilisateur.getEmail());
			pstmt.setString(4,utilisateur.getTelephone());
			pstmt.setString(5, utilisateur.getMotDePasse());
			pstmt.setInt(6, utilisateur.getCredit());
			pstmt.setBoolean(7, utilisateur.isAdministrateur());
			pstmt.setInt(8, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
			
			pstmt.close();
			cnx.close();
		}	catch(Exception e) {
			e.printStackTrace();
		}
		// Mise à jour de l'addresse associé à l'utilisateur NE MARCHE PAS ?
		//AdresseManager.getInstance().update(utilisateur.getAdresse());
	}

	@Override
	public void delete(Utilisateur utilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt;
			
			pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
			pstmt.close();
			
			AdresseManager.getInstance().delete(utilisateur.getAdresse());	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Utilisateur> selectAll() {
		return null;
	}

	@Override
	public Utilisateur selectById(int noUtilisateur) {
		Utilisateur utilisateur = null;
		ResultSet rs;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_ID);
			pstmt.setInt(1, noUtilisateur);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			utilisateur = rsToUtilisateur(rs);
			}
			pstmt.close();
			cnx.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return utilisateur;
	}

	@Override
	public Utilisateur selectByPseudo(String utilisateurPseudo) {
		Utilisateur utilisateur = null;
		ResultSet rs;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO);
			pstmt.setString(1, utilisateurPseudo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			utilisateur = rsToUtilisateur(rs);
			}
			pstmt.close();
			cnx.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return utilisateur;
	}

	@Override
	public Utilisateur login(String pseudo, String mot_de_passe) {
		
		Utilisateur utilisateur=null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LOGIN_PSEUDO);
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
							  AdresseManager.getInstance().findById(rs.getInt("id_adresse")),
							  rs.getString("mot_de_passe"),
							  rs.getInt("credit"),
							  rs.getBoolean("administrateur")
							  );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return utilisateur;
	}

	@Override
	public boolean isEmailOk(String email) {
		boolean isEmailAvailable = false;
		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
		
		PreparedStatement pstmt = cnx.prepareStatement(VERIF_EMAIL);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		isEmailAvailable = !rs.next();
		pstmt.close();
		cnx.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isEmailAvailable;
	}

	@Override
	public boolean isPseudoOk(String pseudo) {
		boolean isPseudoAvailable = false;
		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
		
		PreparedStatement pstmt = cnx.prepareStatement(VERIF_PSEUDO);
		pstmt.setString(1, pseudo);
		ResultSet rs = pstmt.executeQuery();
		isPseudoAvailable = !rs.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isPseudoAvailable;
	}
}
