package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

/**
 * Servlet implementation class UtenteEliminaByUsernameServlet
 */
public class UtenteEliminaByUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteEliminaByUsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession sessione = request.getSession(false);
	        if (sessione == null) {
	            response.sendRedirect("public-jsp/LoginPage.jsp");
	            return;
	        }
	        
	        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
	        if (utente == null || utente.getRuolo() != Ruolo.UTENTE_BASE) {
	            response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
	            return;
	        }
		long idUtente = utente.getId();
		String usernameElimina = request.getParameter("usernameElimina");
	    if (usernameElimina == null || usernameElimina.isEmpty()) {
	        request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	        return;
	    }
	    
	    Utente eliminaUtenteUsername = BusinessLogic.UtenteDeletByNome(idUtente, usernameElimina);
	    if(eliminaUtenteUsername == null) {
        	request.getRequestDispatcher("public-jsp/ErrorPage.jsp");
        	return;
        }
        String messaggioSuccesso = "L'utente con username " + eliminaUtenteUsername + " è stata eliminata con successo.";
    	request.setAttribute("messaggioSuccesso", messaggioSuccesso);
    	response.sendRedirect("public-jsp/HomePagePrincipale.jsp");
	}

}
