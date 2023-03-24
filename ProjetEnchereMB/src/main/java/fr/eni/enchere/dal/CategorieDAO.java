package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Categorie;

public interface CategorieDAO {
	Categorie selectByID(int noCategorie);
	List<Categorie> selectAll();
}
