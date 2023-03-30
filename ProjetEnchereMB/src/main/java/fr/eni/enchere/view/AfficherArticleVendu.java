package fr.eni.enchere.view;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BllException;

/**
 * Servlet implementation class AfficherArticleVendu
 */
@WebServlet("/AfficherArticleVendu")
public class AfficherArticleVendu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request doit contenir un attribut "article" qui est un objet de type Article Vendu
		int id_article = 0;
		if(request.getParameter("id_article")!=null) {
			id_article = Integer.parseInt(request.getParameter("id_article"));
		}else if (id_article == 0) {
			id_article = (int)request.getAttribute("id_article");
		}
		ArticleVendu article = null;
		// Recuperation de l'article de la bdd
					try {
						article = ArticleVenduManager.getInstance().selectArticleById(id_article);
						request.setAttribute("article", article);
						if(article!=null) {
						request.getRequestDispatcher("/WEB-INF/ArticleVendu.jsp").forward(request, response);
						}else {
							request.getRequestDispatcher("encheres").forward(request, response);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de l'utilisateur connecté
		
		HttpSession session  = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        // Récupération des données du formulaire HTTP POST
        
        int montantEnchere; 
        montantEnchere = Integer.parseInt(request.getParameter("proposition"));
        int noArticle = Integer.parseInt(request.getParameter("noArticle"));
        ArticleVendu article = null;
        
        // Récupération de l'article grâce à son numéro
        
		try {
			article = ArticleVenduManager.getInstance().selectArticleById(noArticle);
		} catch (BllException e) {
			e.printStackTrace();
		}
        
        // Vérification de la validité de l'enchère
        
        boolean enchereValide = verifierEnchere(utilisateur, article, montantEnchere);
        
        // Si l'enchère est valide, on le rajoute à la BDD, puis on débite le crédit à l'utilisateur
        // puis en met à jour l'utilisateur de la session, puis on le met à jour dans la BDD
        // enfin on rajoute un petit message ok à afficher dans la JSP
        
        if (enchereValide) {
        	EnchereManager.getInstance().insertEnchere(new Enchere(utilisateur, 
        															article, 
        															LocalDate.now(), 
        															montantEnchere));
        	utilisateur.setCredit(utilisateur.getCredit()-montantEnchere);
        	session.setAttribute("utilisateur", utilisateur);
        	UtilisateurManager.getInstance().modifier(utilisateur);
        	request.setAttribute("ok", "Votre enchère à bien été prise en compte");
        }
        
        // Si l'enchère n'est pas valide on retourne un attribut erreur pour afficher dans la JSP
        
        if (!enchereValide) {
            request.setAttribute("erreur", "L'enchère n'est pas valide, vérifiez la somme et votre credit");
        }
        
        // Retour sur la JSP
        doGet(request, response);
    }

    private boolean verifierEnchere(Utilisateur utilisateur, ArticleVendu article, int montantEnchere) {
    	if (article != null 
    			&& montantEnchere > article.getPrixVente() 
    			&& montantEnchere <= utilisateur.getCredit()
    			&& article.getUtilisateur().getPseudo().equals(utilisateur.getPseudo()))
    		return true;
    	return false;
    }
	
}
