package fr.eni.enchere.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur; 

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant;
		String password;
		Utilisateur utilisateur=null;
		Boolean typeEmail = false;
		// RECUPERATION DES PARAMETRES ENVOYES PAR LA JSP Login
		identifiant = request.getParameter("identifiant");
		if (identifiant.contains("@")) {
			typeEmail = true;
		}
		password = request.getParameter("password");
		
		utilisateur = UtilisateurManager.getInstance().login(identifiant, password, typeEmail);
		
		if(utilisateur!=null) {
			// Creer une session pour l'utilisateur
			HttpSession ses = request.getSession();
			ses.setAttribute("utilisateur", utilisateur);
			Cookie cookieLog;
			cookieLog=new Cookie("DernierId", request.getParameter("identifiant"));
			cookieLog.setMaxAge(60*60*24*7*4); // durée de vie du cookie 1 mois
			response.addCookie(cookieLog);
			request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
		else {
			request.setAttribute("erreur", "L'email ou le mot de passe est invalide");
			doGet(request, response);
		}
		
	}

}
