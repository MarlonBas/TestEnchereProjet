package fr.eni.enchere.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;

/**
 * Servlet implementation class AfficherUtilisateur
 */
@WebServlet("/AfficherUtilisateur")
public class AfficherUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		// request doit contenir un parametre "utilisateur"
		request.setAttribute("utilisateur",UtilisateurManager.getInstance().selectByName(request.getParameter("utilisateur")));
		if (request.getAttribute("utilisateur").equals(ses.getAttribute("utilisateur"))) {
			request.getRequestDispatcher("/WEB-INF/MonCompte.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/Utilisateur.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
