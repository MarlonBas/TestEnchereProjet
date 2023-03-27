package fr.eni.enchere.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.AdresseManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BllException;

/**
 * Servlet implementation class ModifierUtilisateur
 */
@WebServlet("/ModifierUtilisateur")
public class ModifierUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/MonCompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateurNouveau;
		Adresse adresseNouveau;
		HttpSession ses = request.getSession();
		Utilisateur utilisateur = (Utilisateur) ses.getAttribute("utilisateur");
		if (utilisateur == null) {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);

		}

		try {
			adresseNouveau = new Adresse(
					utilisateur.getAdresse().getId_adresse(),
					request.getParameter("rue"),
					Integer.parseInt(request.getParameter("code_postal")),
					request.getParameter("ville")
					);
			utilisateurNouveau=new Utilisateur(
					utilisateur.getNoUtilisateur(),
					utilisateur.getPseudo(),
					request.getParameter("nom"),
					request.getParameter("prenom"),
					request.getParameter("email"),
					request.getParameter("telephone"),
					adresseNouveau,
					utilisateur.getMotDePasse(),
					utilisateur.getCredit(),
					utilisateur.isAdministrateur()
					); 
			UtilisateurManager.getInstance().modifier(utilisateurNouveau);
			AdresseManager.getInstance().update(adresseNouveau);
			ses.setAttribute("utilisateur", utilisateurNouveau);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
