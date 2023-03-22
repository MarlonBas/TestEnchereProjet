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
		request.getRequestDispatcher("Login.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email;
		String password;
		Utilisateur utilisateur=null;
		
		email=request.getParameter("email");
		password=request.getParameter("password");
		
		//En commentaire le temps de mettre login() en BLL
		utilisateur=UtilisateurManager.getInstance().login(email, password);
		
		if(utilisateur!=null) {
			// Creer une session pour l'utilisateur
			HttpSession ses = request.getSession();
			ses.setAttribute("utilisateur", utilisateur);
			Cookie cookieLog;
			cookieLog=new Cookie("DernierEmail", utilisateur.getEmail());
			cookieLog.setMaxAge(60*60*24*7*4); // 1 mois
			response.addCookie(cookieLog);
			response.sendRedirect("Utilisateur.jsp");
		}
		else
		{
			request.setAttribute("erreur", "L'email ou le mot de passe est invalide");
			doGet(request, response);
		}
		
	}

}
