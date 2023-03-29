package fr.eni.enchere.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bo.ArticleVendu;

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
