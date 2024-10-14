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
 * Servlet implementation class UtenteFindByIDServlet
 */
public class UtenteFindByIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteFindByIDServlet() {
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
		String ricerca = request.getParameter("idUtente");

	    if (ricerca == null || ricerca.isBlank()) {
	        String error = "Il campo di ricerca è vuoto.";
	        response.setContentType("text/html");
	        response.getWriter().write("<p style=\"color:red;\">" + error + "</p>");
	        return;
	    }
	    long idUtente = 0;

	    try {
	        idUtente = Long.parseLong(ricerca);
	    } catch (NumberFormatException e) {
	        String error = "ID Utente non valido. Deve essere un numero.";
	        response.setContentType("text/html");
	        response.getWriter().write("<p style=\"color:red;\">" + error + "</p>");
	        return;
	    }
	    Utente uCerca = BusinessLogic.UtenteFindById(idUtente);

	    if (uCerca == null) {
	        response.setContentType("text/html");
	        response.getWriter().write("<p style=\"color:red;\">Nessun utente trovato con questo id</p>");
	        return;
	    }

	    response.setContentType("text/html");
	    response.getWriter().write(
	        "<h2>Dettagli Utente</h2>" +
	        "<table border='1'>" +
	        "<tr><th>ID</th><td>" + uCerca.getId() + "</td></tr>" +
	        "<tr><th>Ruolo</th><td>" + uCerca.getRuolo() + "</td></tr>" +
	        "<tr><th>Username</th><td>" + uCerca.getUsername() + "</td></tr>" +
	        "<tr><th>Email</th><td>" + uCerca.getEmail() + "</td></tr>" +
	        "<tr><th>Data Creazione</th><td>" + uCerca.getData_creazione() + "</td></tr>" +
	        "<tr><th>Data Ultima Modifica</th><td>" + uCerca.getData_modifica() + "</td></tr>" +
	        "</table>"
	    );
	}

}
