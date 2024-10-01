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
 * Servlet implementation class PaginaResetPasswordServlet
 */
@WebServlet("/PaginaResetPasswordServlet")
public class PaginaResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaResetPasswordServlet() {
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
		if (session == null || session.getAttribute("userId") == null) {
			response.getWriter().write("Errore: utente non autenticato.");
			return;
		}

		long idUtente = (Long) session.getAttribute("userId");

		String passwordVecchia = request.getParameter("passwordVecchia");
		String passwordNuova = request.getParameter("passwordNuova");
		String passwordConferma = request.getParameter("passwordConferma");
		
		if (passwordVecchia == null || passwordNuova == null || passwordConferma == null ||
			passwordVecchia.isEmpty() || passwordNuova.isEmpty() || passwordConferma.isEmpty()) {
			response.getWriter().write("Errore: tutti i campi sono obbligatori.");
			return;
		}
		
		if (passwordVecchia.equals(passwordNuova)) {
			response.getWriter().write("Errore: la nuova password non può essere uguale alla vecchia.");
			return;
		}
		
		if (!passwordNuova.equals(passwordConferma)) {
			response.getWriter().write("Errore: la nuova password e la conferma non corrispondono.");
			return;
		}

		Utente utente = BusinessLogic.UtenteFindById(idUtente);
		if (utente == null) {
			response.getWriter().write("Errore: utente non trovato.");
			return;
		}

		if (!utente.getPassword().equals(passwordVecchia)) {
			response.getWriter().write("Errore: la vecchia password non è corretta.");
			return;
		}

		Utente utenteAggiornato = BusinessLogic.updatePassword(idUtente, passwordConferma);
		if (utenteAggiornato != null) {
			response.getWriter().write("Password aggiornata con successo!");
		} else {
			response.getWriter().write("Errore nell'aggiornamento della password.");
		}
		 request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
	}
	
}
