package fr.eni.enchere.view;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BllException;
import fr.eni.enchere.bll.UtilisateurManager;


@WebServlet("/ajouterutilisateur")
public class AjouterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreerUtilisateur.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utilisateur u;
		Adresse adresse;
		
		try {
			adresse = new Adresse(
					request.getParameter("rue"),
					Integer.parseInt(request.getParameter("code_postal")),
					request.getParameter("ville")
					);
			u=new Utilisateur(
					request.getParameter("pseudo"),
					request.getParameter("nom"),
					request.getParameter("prenom"),
					request.getParameter("email"),
					request.getParameter("telephone"),
					adresse,
					request.getParameter("mot_de_passe")
					); 
			
					request.setAttribute("utilisateurEnCreation", u);
			
					// Verification que le mot de passe soit le meme dans la case confirmation et qu'elle n'est pas vide.
			if(request.getParameter("confirmation_mot_de_passe").equals(request.getParameter("mot_de_passe")) && !request.getParameter("mot_de_passe").isEmpty())
			{
				try {
					UtilisateurManager.getInstance().creerUtilisateur(u);
					HttpSession ses = request.getSession();
					ses.setAttribute("utilisateur", u);
					response.sendRedirect("encheres");
				} catch (BllException e) {
			
					request.setAttribute("erreur", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreerUtilisateur.jsp");
					rd.forward(request, response);
					
				}
			}
				else 	
				{
					request.setAttribute("erreur", "Les mots de passes sont différents");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreerUtilisateur.jsp");
					rd.forward(request, response);
						
				}
			
			
		}catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		
		}
	}
}
		
		
		
	
	
	

