package fr.eni.enchere.view;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bo.ArticleVendu;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVendu article = null;
		
		LocalDate debutEnchere = LocalDate.parse(request.getParameter("debutenchere"));
		LocalDate finEnchere = LocalDate.parse(request.getParameter("finenchere"));
		
		int prix = Integer.parseInt(request.getParameter("prix"));
		
		String etatVente = "En cours";
		if (LocalDate.now().isBefore(debutEnchere)){
			etatVente = "Pas encore commencé";
		}
		
		article = new ArticleVendu(
				request.getParameter("nom"),
				request.getParameter("description"),
				debutEnchere,
				finEnchere,
				prix,
				prix,
				etatVente
				);
		
		//A decommenter quand il sera dispo dans ArticleVenduManager
		//ArticleVenduManager.getInstance().creerArticleVendu();
		
		//request.getRequestDispatcher("Article.jsp").forward(request, response);
		doGet(request, response);
	}

}
