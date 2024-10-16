package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Libreria;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


public class LibreriaEliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LibreriaEliminaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String libreriaElimina = request.getParameter("libreriaId");
		
		long idLibreria = 0;
        
        HttpSession sessione = request.getSession(false);
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }
        
        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        if (utente == null || utente.getRuolo() != Ruolo.UTENTE_BASE) {
            response.sendRedirect("public-jsp/ErrorPage.jsp");
            return;
        }
        
        if(libreriaElimina == null || libreriaElimina.isEmpty()) {
        	request.setAttribute("errore", "errore");
        	response.sendRedirect("public-jsp/LoginPage.jsp");
        }
        
        try {
        	
        	idLibreria = Long.parseLong(libreriaElimina);
        	
        }catch(Exception e) {
        	
        	System.out.println("errore");
        }
        
        Libreria libreriaTolta = BusinessLogic.eliminaLibreria(idLibreria);
        
        if(libreriaTolta == null) {
        	String messaggioErrore = "La libreria " + libreriaElimina + " non è stata trovata.";
        	request.setAttribute("messaggioErrore", messaggioErrore);
        	return;
        }
        String messaggioSuccesso = "La libreria " + libreriaElimina + " è stata eliminata con successo.";
    	request.setAttribute("messaggioSuccesso", messaggioSuccesso);
    	response.sendRedirect("LibreriaFindByIdUtenteServlet");
	}

}
