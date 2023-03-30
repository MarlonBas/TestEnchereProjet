package fr.eni.enchere.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class AjouterArticleVendu
 */
@WebServlet("/AjouterArticleVendu")
public class AjouterArticleVendu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categorie> categories = CategorieManager.getInstance().selectAllCategories();
		HttpSession ses = request.getSession();
		
		request.setAttribute("categories", categories);
		if(ses.getAttribute("utilisateur")!=null) {
		request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp").forward(request, response);	
		}else {
		response.sendRedirect("encheres");		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// CONVERSION DES PARAMETRES - de String en int, LocalDate, et Categorie
		LocalDate debutEnchere = LocalDate.parse(request.getParameter("debutenchere"));
		LocalDate finEnchere = LocalDate.parse(request.getParameter("finenchere"));
		int prix = Integer.valueOf(request.getParameter("prix"));
		int codePostal = Integer.parseInt(request.getParameter("codepostal"));
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		Categorie categorie = new Categorie();
		int noCategorie = Integer.parseInt(request.getParameter("categorie"));
		categorie = CategorieManager.getInstance().selectCategorie(noCategorie);
		
		if(prix <= 0 ) {
			request.setAttribute("erreur", "La mise à prix doit être un nombre positif");
		}else if(finEnchere.isBefore(LocalDate.now())){
			request.setAttribute("erreur", "La date de fin d'enchère ne peut pas se situer dans le passé");
		}else if (finEnchere.isBefore(debutEnchere)) {
			request.setAttribute("erreur", "La date de fin d'enchère ne peut pas être avant le début");
		}
		
		if (utilisateur != null) {
			// DETERMINATION DU STATUT DE LA VENTE
			String etatVente = "en cours";
			if (LocalDate.now().isBefore(debutEnchere)){
				etatVente = "pas debute";
			}
			if (LocalDate.now().isAfter(finEnchere)){
				etatVente = "termine";
			}
			
			
			// CREATION DE L'ADDRESSE DE RETRAIT
			Adresse adresse = new Adresse(
					request.getParameter("rue"),
					codePostal,
					request.getParameter("ville")
					);
			
			// CREATION DE L'ARTICLE A VENDRE
			ArticleVendu article = new ArticleVendu(
					request.getParameter("nom"),
					request.getParameter("description"),
					debutEnchere,
					finEnchere,
					prix,
					prix,
					etatVente,
					adresse,
					utilisateur,
					categorie
					);
			
			request.setAttribute("articleEnCreation", article);
			if(request.getAttribute("erreur")!=null) {
				doGet(request,response);
			}else {
				// ENVOIE DE L'ARTICLE A LA BLL SI IL N'Y A PAS D'ERREUR
				 article = ArticleVenduManager.getInstance().creerArticleVendu(article);
				 request.setAttribute("id_article", article.getNoArticle());
				
			}
			request.getRequestDispatcher("encheres").forward(request, response);
		} //"AfficherArticleVendu?id_article="+String.valueOf(article.getNoArticle())

	}
}
