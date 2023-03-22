package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	// LE SINGLETON
	// permet de n'avoir qu'un objet UtilisateurManager
	private static UtilisateurManager instance = null;
	private UtilisateurDAO utilisateurDAO;
	
	private UtilisateurManager(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}
	
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager(DAOFactory.getUtilisateurDAO());
		}
		return instance;
	}
	
	// LES FONCTIONS DE GESTION UTILISATEUR
	
	// CREER UN NOUVEAU UTILISATEUR
	public void creerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.insert(utilisateur);
	}
	
	//LOGIN
	public Utilisateur login(String email,String password) {
		return utilisateurDAO.login(email,password);
	}
	
	//SUPPRIMER UTILISATEUR
	public void deleteById(int id) {
		utilisateurDAO.delete(id);
	}
	
	//MODIFIER UTILISATEUR
	/*public void modifier(Utilisateur utilisateur) {
		utilisateurDAO.update(utilisateur);
	}*/
}
