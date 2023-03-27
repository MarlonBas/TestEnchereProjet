package fr.eni.enchere.dal;

import java.util.List;


import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur);
	public void update(Utilisateur utilisateur);
	void delete(Utilisateur utilisateur);
	public List<Utilisateur> selectAll();
	public Utilisateur selectById(int noUtilisateur);
	public Utilisateur selectByName(String utilisateurName);
	public Utilisateur login(String pseudo, String motDePasse);
	public Utilisateur loginEmail(String email, String motDePasse);
	boolean isEmailOk(String email);
	boolean isPseudoOk(String pseudo);
	
}
