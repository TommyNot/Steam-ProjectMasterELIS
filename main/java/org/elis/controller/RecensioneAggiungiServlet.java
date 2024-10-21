package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Recensione;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

/**
 * Servlet implementation class RecensioneAggiungiServlet
 */
@WebServlet("/RecensioneAggiungiServlet")
public class RecensioneAggiungiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RecensioneAggiungiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		String voto = request.getParameter("voto");
		String testo = request.getParameter("recensione");
		String id = request.getParameter("idGioco");
		
        if (voto == null || voto.isEmpty() || testo == null || testo.isEmpty() || id == null || id.isEmpty()) {
        	System.out.println("primo if");
            request.setAttribute("errore", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("public-jsp/DashboardUtente.jsp").forward(request, response);
            return;
        }
        
        int votoRecensione;
        try {
            votoRecensione = Integer.parseInt(voto);
            if (votoRecensione <= 0) {
                System.out.println("errore voto minore di 0");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore nel formato del voto: " + e.getMessage());
            request.getRequestDispatcher("public-jsp/DashboardUtente.jsp").forward(request, response);
            System.out.println("Errore nel voto.");
            return;
        }
        
     
	        	
			  long idGioco = Long.parseLong(id);
	    
		  
		  //trovo il gioco dall'id
		  Gioco gioco = BusinessLogic.findGiocoById(idGioco);
		  
		  //controllo la sessione e mi prendo l'oggetto utente per costruirmi quello recensione
		  HttpSession sessione = request.getSession(false);
		   if (sessione == null) {
	            response.sendRedirect("public-jsp/PaginaLogin.jsp");
	            return;
	        }

	        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
	        
	        if (utente != null) {
	            long idUtente =  utente.getId();
	            System.out.println("ID Utente loggato: " + idUtente);
	            
	            Utente u = BusinessLogic.UtenteFindById(idUtente);
	            if (u != null) {
	                boolean isUtenteBase= u.getRuolo() == Ruolo.UTENTE_BASE;
	                if (isUtenteBase) {
	                    System.out.println("L'utente è un utente base.");
	                    Recensione aggiunta = new Recensione(0, LocalDateTime.now(), LocalDateTime.now(), votoRecensione, testo, gioco, utente );
	                    BusinessLogic.RecensioneAdd(aggiunta);
	                    response.sendRedirect(request.getContextPath() + "/GiocoVediDettagli?barraRicerca=" + idGioco);
	                    if (aggiunta != null) {
	                    	 request.setAttribute("successo", "Recensione creata con successo.");
	                    } 
	                } else {
	                	response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
	                }
	            } else {
	            	response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
	            }
	        } else {
	        	response.sendRedirect("public-jsp/PaginaLogin.jsp");
	        }
        
	}

}
