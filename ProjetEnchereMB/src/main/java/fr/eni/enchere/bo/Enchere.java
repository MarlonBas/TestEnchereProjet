package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Enchere {
	
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Utilisateur Utilisateur;
	private ArticleVendu articleVendu;

	public Enchere() {
		super();
	}
	
	public Enchere(Utilisateur Utilisateur, ArticleVendu articleVendu, LocalDate dateEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.Utilisateur = Utilisateur;
		this.articleVendu = articleVendu;
	}
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	public Utilisateur getUtilisateur() {
		return Utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		Utilisateur = utilisateur;
	}
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		articleVendu = articleVendu;
	}
	
}
