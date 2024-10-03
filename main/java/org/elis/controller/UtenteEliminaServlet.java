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
 * Servlet implementation class UtenteEliminaServlet
 */
public class UtenteEliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteEliminaServlet() {
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

	    Object utenteIdObj = session.getAttribute("utenteId");
	    if (!(utenteIdObj instanceof Long)) {
	        response.getWriter().write("Errore: Utente non autenticato.");
	        return;
	    }
	    long idUtente = (Long) utenteIdObj;

	    String password = request.getParameter("password");
	    if (password == null || password.isEmpty()) {
	        request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
	        return;
	    }

	    Utente eliminaUtente = BusinessLogic.UtenteDeletByPassword(idUtente, password);
	    if (eliminaUtente != null) {
	        response.getWriter().write("Account eliminato");
	        return; 
	    } else {
	        request.setAttribute("errorMessage", "Password non valida o errore durante l'eliminazione dell'account.");
	        request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
	        return; 
	    }
	}
}

