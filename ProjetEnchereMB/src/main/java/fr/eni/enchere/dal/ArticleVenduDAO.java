package fr.eni.enchere.dal;

import java.sql.SQLException;

import java.util.List;


import fr.eni.enchere.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	public List<ArticleVendu> selectAll() throws  SQLException;
	public ArticleVendu selectById(int id_article);
	public void delete(ArticleVendu articleVendu);
    public void insert (ArticleVendu articleVendu );
	public void update(ArticleVendu articleVendu);
}
