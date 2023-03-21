package fr.eni.enchere.dal;

import java.util.List;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur);
	public void delete(int noUtilisateur);
	public List<Utilisateur> selectAll();
	public Utilisateur selectById(int noUtilisateur);
	
}
