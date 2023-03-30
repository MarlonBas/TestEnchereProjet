package fr.eni.enchere.bll;

import java.util.Iterator;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.EnchereDAO;

public class EnchereManager {
	
	private static EnchereManager instance = null;
	private EnchereDAO EnchereDAO;

	private EnchereManager(EnchereDAO enchereDAO) {              
		this.EnchereDAO = enchereDAO;                              
	}                                                                  
	
	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager(DAOFactory.getEnchereDAO());
		}
		return instance;
	}
	
	public Enchere selectEnchere(int idEnchere) {
		return EnchereDAO.selectByID(idEnchere);
	}
	public List<Enchere>selectEncheresByIdArticle(int id_article){
		return EnchereDAO.selectByIdArticle(id_article);
	}
	public List<Enchere> selectAllEncheres() {
		return EnchereDAO.selectAll();
	}
	
	public void insertEnchere(Enchere enchere) {
		rembourserPrecedent(enchere);
		EnchereDAO.insert(enchere);
		
	}
	
	public void deleteEnchere(int idEnchere) {
		EnchereDAO.delete(idEnchere);
	}
	public Enchere meilleureEnchere(List<Enchere> encheres) {
		Enchere meilleurEnchere =  new Enchere();
		for (Enchere enchere : encheres) {
			if (enchere.getMontantEnchere() > meilleurEnchere.getMontantEnchere())
				meilleurEnchere = enchere;
		}
		return meilleurEnchere;
		
	}
	
	public void rembourserPrecedent(Enchere enchere) {
		List<Enchere> encheres = selectEncheresByIdArticle(enchere.getArticleVendu().getNoArticle());
		
		int recredit = 0;
		int noUtilisateurARecrediter = 0;
		for (Enchere enchereList : encheres) {
			if (enchereList.getMontantEnchere() > recredit) {
				recredit = enchereList.getMontantEnchere();
				noUtilisateurARecrediter = enchereList.getUtilisateur().getNoUtilisateur();
			}
		 }
		Utilisateur utilisateurARecrediter = UtilisateurManager.getInstance().selectById(noUtilisateurARecrediter);
		utilisateurARecrediter.setCredit(utilisateurARecrediter.getCredit() + recredit);
		UtilisateurManager.getInstance().modifier(utilisateurARecrediter);
	}
	
	public void payerVendeur(ArticleVendu article) {
		List<Enchere> encheres = selectEncheresByIdArticle(article.getNoArticle());
		if (!encheres.isEmpty()) {
			Enchere enchereGagnant = meilleureEnchere(encheres);
			Utilisateur vendeur = article.getUtilisateur();
			vendeur.setCredit(vendeur.getCredit()+enchereGagnant.getMontantEnchere());
			UtilisateurManager.getInstance().modifier(vendeur);
		}
	}
}
