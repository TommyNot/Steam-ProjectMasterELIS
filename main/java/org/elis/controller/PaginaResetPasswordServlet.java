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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("utenteLoggato") == null) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			System.out.println("errore 1");
			return;
		}

		long idUtente = (Long) session.getAttribute("utenteLoggato");

		String passwordVecchia = request.getParameter("passwordVecchia");
		String passwordNuova = request.getParameter("passwordNuova");
		String passwordConferma = request.getParameter("passwordConferma");
		
		if (passwordVecchia == null || passwordNuova == null || passwordConferma == null ||
			passwordVecchia.isEmpty() || passwordNuova.isEmpty() || passwordConferma.isEmpty()) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			System.out.println("errore 2");
			return;
		}
		
		if (passwordVecchia.equals(passwordNuova)) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			System.out.println("errore 3");
			return;
		}
		
		if (!passwordNuova.equals(passwordConferma)) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			System.out.println("errore 4");
			return;
		}

		Utente utente = BusinessLogic.UtenteFindById(idUtente);
		if (utente == null) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			System.out.println("errore 5");
			return;
		}

		if (!utente.getPassword().equals(passwordVecchia)) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			System.out.println("errore 6");
			return;
		}

		Utente utenteAggiornato = BusinessLogic.updatePassword(idUtente, passwordConferma);
		if (utenteAggiornato != null) {
			response.getWriter().write("Password aggiornata con successo!");
		} else {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			System.out.println("errore 7");
		}
		 request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
	}
	
}
