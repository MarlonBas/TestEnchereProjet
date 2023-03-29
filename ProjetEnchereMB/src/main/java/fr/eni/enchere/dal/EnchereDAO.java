package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;

public interface EnchereDAO {

	void insert(Enchere enchere);

	void delete(int Enchere);
	
	public Enchere selectByID(int idEnchere);
	
	public List<Enchere> selectAll();

}
