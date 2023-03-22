package fr.eni.enchere.view;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bll.UtilisateurManager;


@WebServlet("/ajouterutilisateur")
public class AjouterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Utilisateur.jsp");
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
					request.getParameter("telephone"),
					request.getParameter("email"),
					adresse,
					request.getParameter("mot_de_passe")
					); 
			
					request.setAttribute("utilisateurEnCreation", u);
			
			if(request.getParameter("confirmation_mot_de_passe").equals(request.getParameter("mot_de_passe")) && !request.getParameter("mot_de_passe").isEmpty())
			{
				UtilisateurManager.getInstance().creerUtilisateur(u);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/MonCompte.jsp");
				rd.forward(request, response);
				
			} else 	
				{
						
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Utilisateur.jsp");
						rd.forward(request, response);
				}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		
		}
		
		}
		
		
	}
	

