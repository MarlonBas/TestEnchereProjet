package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BllException;

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
	public void creerUtilisateur(Utilisateur utilisateur) throws BllException {
		
		boolean verifEmail = utilisateurDAO.isEmailOk(utilisateur.getEmail());
		boolean verifPseudo = utilisateurDAO.isPseudoOk(utilisateur.getPseudo());
		
		if(verifEmail && verifPseudo) {
			utilisateurDAO.insert(utilisateur);
		}else if(verifEmail && !verifPseudo) {
			throw new BllException("Le pseudo est déjà utilisé");
		}else if(!verifEmail && verifPseudo) {
			throw new BllException ("L'email est déjà utilisé");
		}else {
			throw new BllException ("Le pseudo et l'email sont déjà utilisés");
		}
		
		
	}
	
	//LOGIN
	public Utilisateur login(String login,String password, boolean typeEmail) {
		if (typeEmail == true) {
			return utilisateurDAO.loginEmail(login,password);
		}
		return utilisateurDAO.login(login,password);
	}
	
	//SUPPRIMER UTILISATEUR
	public void deleteById(int id) {
		utilisateurDAO.delete(id);
	}
	
	//MODIFIER UTILISATEUR
	public void modifier(Utilisateur utilisateur) {
		utilisateurDAO.update(utilisateur);
	}
}
