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
 * Servlet implementation class UtenteFindByNameServlet
 */
public class UtenteFindByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteFindByNameServlet() {
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
	    
	    String ricerca = request.getParameter("username");
	    
	    if (ricerca == null || ricerca.isBlank()) {
	        String error = "Il campo di ricerca è vuoto.";
	        request.setAttribute("errorMessage", error);
	        return;
	    }

	    Utente uCerca = BusinessLogic.UtenteFindByName(ricerca);

	    if (uCerca == null) {
	    	StringBuilder sb = new StringBuilder();
	       sb.append("<p>");
	       sb.append("nessun utente trovato con questo id");
	       sb.append("</p>");
	        return;
	    }

	    request.setAttribute("utente", uCerca);
	}
}