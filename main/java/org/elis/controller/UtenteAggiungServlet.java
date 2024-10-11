package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.jdbc.UtenteDaoJDBC;
import org.elis.model.Ruolo;
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    if (username == null || email == null || password == null || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
	        request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
	        return;
	    }
	    Utente u=new Utente(Ruolo.UTENTE_BASE,username,email,password);
	    Utente nuovoUtente = BusinessLogic.UtenteAdd(u);


	    if (nuovoUtente != null) {
	        response.getWriter().write("Utente aggiunto con successo! ID: " + nuovoUtente.getId());
	        request.getRequestDispatcher("private-jsp/DashboardUtente.jsp").forward(request, response);
	        
	    }

	    request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
	}

}
