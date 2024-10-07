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

/**
 * Servlet implementation class EliminaGioco
 */
public class GiocoEliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoEliminaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String eliminaGiocoNome = request.getParameter("productId");
        
        long idGioco=0;
        
        HttpSession sessione = request.getSession(false);
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }
        


        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        if (utente == null || utente.getRuolo() != Ruolo.PUBLISHER) {
            response.sendRedirect("public-jsp/AccessoNegato.jsp");
            return;
        }
        
        
        if (eliminaGiocoNome == null || eliminaGiocoNome.isBlank()) {
            String errore = "Il nome del gioco non può essere vuoto.";
            request.setAttribute("errore", errore); 
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
            return; 
        }
        
       
        
        try {
        	
        	idGioco = Long.parseLong(eliminaGiocoNome);
        	
        }catch(Exception e) {
        	
        	System.out.println("errore");
        }

        
        Gioco giocoEliminato = BusinessLogic.eliminaGioco(idGioco);

        
        if (giocoEliminato != null) {
        	
            String successo = "Il gioco '" + eliminaGiocoNome + "' è stato eliminato con successo.";
            request.setAttribute("successo", successo);
        } else {
            String errore = "Il gioco con nome '" + eliminaGiocoNome + "' non è stato trovato.";
            request.setAttribute("errore", errore);
        }

       
        request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
    }

}
