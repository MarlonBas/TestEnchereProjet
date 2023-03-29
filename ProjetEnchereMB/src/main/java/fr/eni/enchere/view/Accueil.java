package fr.eni.enchere.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.exceptions.BllException;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/encheres")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// RECUPERATION DE LA BDD ET STOCKAGE DES LISTES DANS LA REQUETE - 
		// MISE EN COMMENTAIRE PARCE QUE PEUT-ETRE PAS NECESSAIRE
		/*List<Categorie> categories = CategorieManager.getInstance().selectAllCategories();
		request.setAttribute("categories", categories);
		try {
			List<ArticleVendu> articles = ArticleVenduManager.getInstance().getAllArticleVendu();
			request.setAttribute("articles", articles);
		} catch (BllException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Enchere> encheres = EnchereManager.getInstance().selectAllEncheres();
		request.setAttribute("encheres", categories);*/
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
