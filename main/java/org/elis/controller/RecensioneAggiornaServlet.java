package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


@WebServlet("/RecensioneAggiornaServlet")
public class RecensioneAggiornaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RecensioneAggiornaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession sessione = request.getSession(false);
	        if (sessione == null) {
	            response.sendRedirect("public-jsp/PaginaLogin.jsp");
	            return;
	        }
	        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
	        if (utente == null || (utente.getRuolo() != Ruolo.UTENTE_BASE && utente.getRuolo() != Ruolo.ADMIN)) {
	            response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
	            return;
	        }

	        
	        try {
	        	long idRecensione = 0;
	        	long idGioco = 0;
	        	String idGiocoString = request.getParameter("idGioco");
	        	String idRecensioneString = request.getParameter("idRecensione");
	        	String testo = request.getParameter("testo");
	        	String voto = request.getParameter("voto");
	        	
	        	idRecensione = Long.parseLong(idRecensioneString);
	        	idGioco = Long.parseLong(idGiocoString);
	        	
	        	 if (testo != null && !testo.isEmpty()) {
	                 BusinessLogic.updateRecensioneTesto(idRecensione, testo);
	                 System.out.println("Testo aggiornato con successo");
	                 response.sendRedirect(request.getContextPath() + "/GiocoVediDettagli?barraRicerca=" + idGioco);
	             }
	        	 
	        	  if (voto != null && !voto.isEmpty()) {
	                  try {
	                      int valutazione = Integer.parseInt(voto);
	                      BusinessLogic.updateRecensioneVoto(idRecensione, valutazione);
	                      System.out.println("Voto aggiornato con successo");
	                  } catch (NumberFormatException e) {
	                      request.setAttribute("errorMessage", "Formato del voto non valido.");
	                      request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	                      return;
	                  }
	              }
	        	  
	        	
	        } catch (NumberFormatException e) {
	            System.out.println("Errore nel formato dell'ID della recensione: " + e.getMessage());
	            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	        }
	        
	        
	}

}
