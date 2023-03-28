package fr.eni.enchere.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.exceptions.BllException;

/**
 * Servlet implementation class Recherche
 */
@WebServlet("/Recherche")
public class Recherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récup des parametres des filtres
			int categorie = Integer.parseInt(request.getParameter("categorie"));
			String search = request.getParameter("recherche");
			List<ArticleVendu> articles;
			
			if (search.equals("/")) {
				search = null;
			}
		// Recuperation des articles de la BDD
			try {
				articles = ArticleVenduManager.getInstance().getAllArticleVendu();
					
				// Filtres (avec objet Iterator, c'est plutôt classe ce truc, ça evite des problemes d'index quand tu remove le dernier element etc...)
				if (search != null) {
				    Iterator<ArticleVendu> iterator = articles.iterator();
				    while (iterator.hasNext()) {
				        ArticleVendu article = iterator.next();
				        if (!article.getNomArticle().contains(search) && !article.getDescription().contains(search)) {
				            iterator.remove();
				        }
				    }
				}

				if (categorie != 0) {
				    Iterator<ArticleVendu> iterator = articles.iterator();
				    while (iterator.hasNext()) {
				        ArticleVendu article = iterator.next();
				        if (article.getCategorie().getNoCategorie() != categorie) {
				            iterator.remove();
				        }
				    }
				}
				
				// Passage de la liste d'articles filtrés au request pour retour à la JSP
				request.setAttribute("articles",articles);
			} catch (BllException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// Retour sur l'accueil avec la liste d'articles filtrés
			request.getRequestDispatcher("WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
