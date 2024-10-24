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
	        request.setAttribute("errore", "Tutti i campi sono obbligatori.");
	        request.getRequestDispatcher("public-jsp/DashboardUtente.jsp").forward(request, response);
	        return;
	    }

	    int votoRecensione;
	    try {
	        votoRecensione = Integer.parseInt(voto);
	        if (votoRecensione <= 0) {
	            request.setAttribute("errore", "Il voto deve essere maggiore di zero.");
	            request.getRequestDispatcher("public-jsp/DashboardUtente.jsp").forward(request, response);
	            return;
	        }
	    } catch (NumberFormatException e) {
	        request.setAttribute("errore", "Errore nel formato del voto: " + e.getMessage());
	        request.getRequestDispatcher("public-jsp/DashboardUtente.jsp").forward(request, response);
	        return;
	    }

	    long idGioco = Long.parseLong(id);
	    Gioco gioco = BusinessLogic.findGiocoById(idGioco);
	    
	    
	    HttpSession sessione = request.getSession(false);
	    if (sessione == null) {
	        response.sendRedirect("public-jsp/PaginaLogin.jsp");
	        return;
	    }

	    Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
	    
	    if (utente != null) {
	        long idUtente = utente.getId();
	        Utente u = BusinessLogic.UtenteFindById(idUtente);

	        if (u != null) {
	            if (u.getRuolo() == Ruolo.UTENTE_BASE) {
	                Recensione aggiunta = new Recensione(0, LocalDateTime.now(), LocalDateTime.now(), votoRecensione, testo, gioco, utente);
	                BusinessLogic.RecensioneAdd(aggiunta);
	                request.setAttribute("successo", "Recensione creata con successo.");
	                response.sendRedirect(request.getContextPath() + "/GiocoVediDettagli?barraRicerca=" + idGioco);
	            } else if (u.getRuolo() == Ruolo.PUBLISHER || u.getRuolo() == Ruolo.ADMIN) {
	              
	                response.sendRedirect(request.getContextPath() + "/GiocoVediDettagli?barraRicerca=" + idGioco);
	            }
	        } else {
	            response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
	        }
	    } else {
	        response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
	    }
	}
}