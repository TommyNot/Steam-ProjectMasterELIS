package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Offerta;
import org.elis.model.Recensione;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


@WebServlet("/RecensioneFindByUtente")
public class RecensioneFindByUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RecensioneFindByUtente() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.getRequestDispatcher("public-jsp/PaginaLogin.jsp");
			return;
		}
		
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		
		if(utente != null) {
			long idUtente = utente.getId();
			System.out.println("ID Utente loggato: " + idUtente);
			
			Utente u = BusinessLogic.UtenteFindById(idUtente);
			if(u != null) {
	                boolean isUtenteBase= u.getRuolo() == Ruolo.UTENTE_BASE;
	                if (isUtenteBase) {
	                    System.out.println("L'utente è un Utente Base.");
	                    List<Recensione> recensioniUtente = BusinessLogic.TrovaRecensioneByIdUtente(idUtente);
	                    if (recensioniUtente == null || recensioniUtente.isEmpty()) {
	                        request.setAttribute("Error", "Nessuna recensione trovata per l'utente selezionato.");
	                        request.getRequestDispatcher("public-jsp/DashboardUtente.jsp").forward(request, response);
	                        return;
	                    }
	                }else {
	                	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	                	response.getWriter().write("Non sei un utente base.");
	                }
	                
			}else {
            	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            	response.getWriter().write("Errore: utente non trovato con ID: " + idUtente);
            }
		}else {
        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        	response.getWriter().write("Nessun utente loggato trovato nella sessione.");
        }
	       
	  
	}

}
