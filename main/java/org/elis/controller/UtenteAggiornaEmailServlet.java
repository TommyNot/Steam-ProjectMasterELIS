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
 * Servlet implementation class UtenteAggiornaEmailServlet
 */
public class UtenteAggiornaEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteAggiornaEmailServlet() {
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
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("utenteId") == null) {
	        response.getWriter().write("Errore: Utente non autenticato.");
	        return;
	    }
	    
	    long idUtente = (long) session.getAttribute("utenteId");
	    
	    String nuovaEmail = request.getParameter("nuovaEmail");

	    if (nuovaEmail == null || nuovaEmail.isEmpty()) {
	        response.getWriter().write("Errore: La nuova email è obbligatoria!");
	        return;
	    }

	    Utente utenteAggiornato = BusinessLogic.updateEmail(idUtente, nuovaEmail);

	    if (utenteAggiornato != null) {
	        response.getWriter().write("Email aggiornata con successo! Nuova email: " + utenteAggiornato.getEmail());
	    } else {
	        response.getWriter().write("Errore nell'aggiornamento dell'email.");
	    }

	    request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
	}


}
