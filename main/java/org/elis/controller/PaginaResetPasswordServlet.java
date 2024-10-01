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
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			return;
		}

		long idUtente = (Long) session.getAttribute("userId");

		String passwordVecchia = request.getParameter("passwordVecchia");
		String passwordNuova = request.getParameter("passwordNuova");
		String passwordConferma = request.getParameter("passwordConferma");
		
		if (passwordVecchia == null || passwordNuova == null || passwordConferma == null ||
			passwordVecchia.isEmpty() || passwordNuova.isEmpty() || passwordConferma.isEmpty()) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			return;
		}
		
		if (passwordVecchia.equals(passwordNuova)) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			return;
		}
		
		if (!passwordNuova.equals(passwordConferma)) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			return;
		}

		Utente utente = BusinessLogic.UtenteFindById(idUtente);
		if (utente == null) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			return;
		}

		if (!utente.getPassword().equals(passwordVecchia)) {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
			return;
		}

		Utente utenteAggiornato = BusinessLogic.updatePassword(idUtente, passwordConferma);
		if (utenteAggiornato != null) {
			response.getWriter().write("Password aggiornata con successo!");
		} else {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
		}
		 request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
	}
	
}
