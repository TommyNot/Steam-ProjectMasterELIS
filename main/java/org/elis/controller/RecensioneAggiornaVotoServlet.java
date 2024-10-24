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

@WebServlet("/RecensioneAggiornaVotoServlet")
public class RecensioneAggiornaVotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RecensioneAggiornaVotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   String voto = request.getParameter("voto");
	   String id = request.getParameter("idRecensione");
	   
	   HttpSession session = request.getSession(false);
		if(session == null) {
			request.getRequestDispatcher("public-jsp/PaginaLogin.jsp");
			return;
		}
		
		if (voto == null || voto.isBlank() || id == null || id.isBlank()) {
            String errore = "L'id o il voto della recensione non può essere vuoto.";
            request.setAttribute("errore", errore); 
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
            return; 
        }
		
		int nuovoVoto;
	    try {
            nuovoVoto = Integer.parseInt(voto);
            if (nuovoVoto <= 0) {
                System.out.println("errore voto minore di 0");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore nel formato del voto: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
            System.out.println("Errore nel voto");
            return;
        }
	    
	    long idRecensione = 0;
		  
		  try {
	        	
	        	idRecensione = Long.parseLong(id);
	        	
	        }catch(Exception e) {
	        	
	        	System.out.println("errore");
	        }

      Utente utente = (Utente) session.getAttribute("utenteLoggato");
      
      if (utente != null) {
          long idUtente =  utente.getId();
          System.out.println("ID Utente loggato: " + idUtente);
          
          Utente u = BusinessLogic.UtenteFindById(idUtente);
          if (u != null) {
              boolean isUtenteBase= u.getRuolo() == Ruolo.UTENTE_BASE;
              if (isUtenteBase) {
                  System.out.println("L'utente è un utente base.");
                  Recensione aggiornata = BusinessLogic.updateRecensioneVoto(idRecensione, nuovoVoto);
                  if (aggiornata != null) {
                      response.sendRedirect("WEB-INF/private-jsp/DashboardUtente.jsp");
                  } 
              } else {
                  System.out.println("L'utente non è un Utente base.");
                  request.getRequestDispatcher("public-jsp/ErrorAccessoNegatoPage.jsp").forward(request, response);
              }
          } else {
              System.out.println("Errore: utente non trovato con ID: " + idUtente);
              request.getRequestDispatcher("public-jsp/ErrorAccessoNegatoPage.jsp").forward(request, response);
          }
      } else {
          System.out.println("Nessun utente loggato trovato nella sessione.");
          request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
      }
	}

}
