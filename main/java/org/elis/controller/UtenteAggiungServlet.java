package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.jdbc.UtenteDaoJDBC;
import org.elis.model.Utente;

/**
 * Servlet implementation class UtenteAggiungServlet
 */
public class UtenteAggiungServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteAggiungServlet() {
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
		String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    if (username == null || email == null || password == null || 
	            username.isEmpty() || email.isEmpty() || password.isEmpty()) {
	        response.getWriter().write("Errore: tutti i campi sono obbligatori!");
	        return;
	    }

	    Utente nuovoUtente = BusinessLogic.UtenteAdd(username, email, password);


	    if (nuovoUtente != null) {
	        response.getWriter().write("Utente aggiunto con successo! ID: " + nuovoUtente.getId());
	    } else {
	        response.getWriter().write("Errore nell'aggiunta dell'utente.");
	    }
	}

}
