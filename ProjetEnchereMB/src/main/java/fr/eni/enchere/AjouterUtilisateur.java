package fr.eni.enchere;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bo.Adresse;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.dal.UtilisateurDAO;


@WebServlet("/ajouterutilisateur")
public class AjouterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("roles", new .UtilisateurDAO().selectAll());
		getServletContext().getRequestDispatcher("/WEB-INF/ajout.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utilisateur u;
		try {
			u=new Utilisateur(request.getParameter("nom"),
					request.getParameter("prenom"), 
					request.getParameter("email"),
					request.getParameter("motDePasse"),
					LocalDate.of(2000, 12, 31), 
						new Adresse(Integer.parseInt(request.getParameter("numero")),
								     request.getParameter("rue"),
									 Integer.parseInt(request.getParameter("codePostal")),
									 request.getParameter("ville")),
						new UtilisateurDAO().getRoleById(Integer.parseInt(request.getParameter("role"))), 12332234l);
			UtilisateurManager.getInstance().creerUtilisateur(u);
			
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		response.sendRedirect("index.html");
		
	}

}
