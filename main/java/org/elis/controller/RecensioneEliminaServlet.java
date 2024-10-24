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
import org.elis.model.Recensione;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


@WebServlet("/RecensioneEliminaServlet")
public class RecensioneEliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RecensioneEliminaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("idRecensione");
		String idGiocoString = request.getParameter("idGioco");
		
		  if (id == null || id.isBlank()) {
	            String errore = "L'id della recensione non può essere vuoto.";
	            request.setAttribute("errore", errore); 
	            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
	            return; 
	        }
		  
        long idRecensione = 0;
		  
		  try {
	        	
	        	idRecensione = Long.parseLong(id);
	        	
	        }catch(Exception e) {
	        	
	        	System.out.println("errore");
	        }
		  
		  	Long idGioco = Long.parseLong(idGiocoString);
		  
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
	                boolean isUtenteBase= u.getRuolo() == Ruolo.UTENTE_BASE || u.getRuolo() == Ruolo.ADMIN;
	                if (isUtenteBase) {
	                    System.out.println("L'utente è un Utente Base.");
	                    Recensione eliminata = BusinessLogic.eliminaRecensione(idRecensione);
	                    response.sendRedirect(request.getContextPath() + "/GiocoVediDettagli?barraRicerca=" + idGioco);
	                    if (eliminata != null) {
	                    	response.getWriter().write("Recensione eliminata con successo.");
	                    } 
	                } else {
	                	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	                	response.getWriter().write("Errore:Non hai i poteri per fare questa operazione .");
	                	request.getRequestDispatcher("public-jsp/ErrorAccessoNegatoPage.jsp").forward(request, response);
	                }
	            } else {
	            	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            	response.getWriter().write("Errore: utente non trovato con ID: " + idUtente);
	            	request.getRequestDispatcher("public-jsp/ErrorAccessoNegatoPage.jsp").forward(request, response);
	            }
	        } else {
	        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        	response.getWriter().write("Nessun utente loggato trovato nella sessione.");
	        	request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
	        }
	}

}
