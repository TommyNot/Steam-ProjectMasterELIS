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
 * Servlet implementation class AdminEliminaServlet
 */
public class AdminEliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEliminaServlet() {
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
	    long id = 0;
	    try {
	        id = Long.parseLong(request.getParameter("id"));
	    } catch (NumberFormatException e) {
	        response.getWriter().write("Errore: ID non valido.");
	        return;
	    }

	    String username = request.getParameter("username");

	    if (username == null || username.isEmpty()) {
	        response.getWriter().write("Errore: Username non valido o mancante.");
	        return;
	    }
	    

	    Utente eliminaUtente = BusinessLogic.UtenteDeletByNome(id, username);

	    if (eliminaUtente != null) {
	    	 response.getWriter().write("successo Account eliminato con successo.");
        } else {
        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        	response.getWriter().write("Errore: Account non trovato o eliminazione fallita.");
        }

       
    }


}
