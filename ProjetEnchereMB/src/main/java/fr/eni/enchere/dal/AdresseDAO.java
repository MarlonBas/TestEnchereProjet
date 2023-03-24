package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Adresse;

public interface AdresseDAO {
	
	public Adresse insert(Adresse adresse);
	public void update(Adresse adresse);
	public void delete(Adresse adresse);
	public Adresse selectById(int no_utilisateur);

}
