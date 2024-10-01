package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		String nuovoUsername = request.getParameter("nuovoUsername");
		
		if (idParam == null || nuovoUsername == null || idParam.isEmpty() || nuovoUsername.isEmpty()) {
			response.getWriter().write("Errore: ID e nuovo nome utente sono obbligatori!");
			return;
		}
		long idUtente;
		try {
			idUtente = Long.parseLong(idParam);
		} catch (NumberFormatException e) {
			response.getWriter().write("Errore: ID utente non valido.");
			return;
		}
		Utente utenteAggiornato = BusinessLogic.UpdateUsername(idUtente, nuovoUsername);
		
		if (utenteAggiornato != null) {
			response.getWriter().write("Nome utente aggiornato con successo! Nuovo username: " + utenteAggiornato.getUsername());
		} else {
			response.getWriter().write("Errore nell'aggiornamento del nome utente.");
		}
	}

}
