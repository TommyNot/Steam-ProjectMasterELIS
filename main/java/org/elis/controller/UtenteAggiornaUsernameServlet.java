package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Utente;

/**
 * Servlet implementation class UtenteAggiornaUsernameServlet
 */
public class UtenteAggiornaUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteAggiornaUsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    
	    if (session == null) {
	        request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
	        return;
	    }
	    
	    Utente u = (Utente) session.getAttribute("utenteLoggato");
	    long idUtente = u.getId();
	    
	    String nuovoUsername = request.getParameter("nuovoUsername");

	    if (nuovoUsername == null || nuovoUsername.isEmpty()) {
	    	request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	        return;
	    }

	    Utente utenteAggiornato = BusinessLogic.UpdateUsername(idUtente, nuovoUsername);

	    if (utenteAggiornato != null) {
	         String success = "Modifica username avvenuta con successo , rifai il login";
	        session.invalidate();
	        request.setAttribute("Success", success);
	    	 request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
	    	 
	    	
	    } else {
	    	request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	    }

	    request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
	}


}
