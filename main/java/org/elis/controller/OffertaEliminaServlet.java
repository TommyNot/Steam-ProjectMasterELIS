package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

@WebServlet("/OffertaEliminaServlet")
public class OffertaEliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public OffertaEliminaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String eliminaOffertaNome = request.getParameter("offertaNome");
		
		
		
		HttpSession sessione = request.getSession(false);
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }
        
        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        if (utente == null || utente.getRuolo() != Ruolo.ADMIN) {
            response.sendRedirect("public-jsp/AccessoNegato.jsp");
            return;
        }
        
        if (eliminaOffertaNome == null || eliminaOffertaNome.isBlank()) {
            String errore = "Il nome dell'offerta non può essere vuoto.";
            request.setAttribute("errore", errore); 
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardAdmin.jsp").forward(request, response);
            return; 
        }
        

	}
}
