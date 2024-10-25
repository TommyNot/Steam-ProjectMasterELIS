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
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


@WebServlet("/OffertaAggiornaScontoServlet")
public class OffertaAggiornaScontoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OffertaAggiornaScontoServlet() {
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
		
		String sconto = request.getParameter("sconto");
		String id = request.getParameter("id");
		
		if (sconto == null || sconto.isBlank() || id == null || id.isBlank()) {
            String errore = "L'id o lo sconto dell'offerta non può essere vuoto.";
            request.setAttribute("errore", errore); 
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardAdmin.jsp").forward(request, response);
            return; 
        }
		
		
		double nuovoSconto;
	    try {
            nuovoSconto = Double.parseDouble(sconto);
            if (nuovoSconto <= 0) {
                System.out.println("errore sconto minore di 0");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            	response.getWriter().write("lo sconto non puo' essere 0 o negativo");
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore nel formato dello sconto: " + e.getMessage());
            request.getRequestDispatcher("public-jsp/DashboardAdmin.jsp").forward(request, response);
            System.out.println("Errore nello sconto");
            return;
        }
		
	    long idOfferta = 0;
		  
		  try {
	        	
	        	idOfferta = Long.parseLong(id);
	        	
	        }catch(Exception e) {
	        	
	        	System.out.println("errore");
	        }

        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        
        
       
        if (utente != null) {
            long idUtente =  utente.getId();
            System.out.println("ID Utente loggato: " + idUtente);
            
            Utente u = BusinessLogic.UtenteFindById(idUtente);
            if (u != null) {
                boolean isAdmin= u.getRuolo() == Ruolo.ADMIN;
                if (isAdmin) {
                    System.out.println("L'utente è un Admin.");
                    Offerta aggiornata = BusinessLogic.updateScontoOfferta(idOfferta, nuovoSconto);
                    if (aggiornata != null) {
                    	response.getWriter().write("Sconto aggiornata con successo.");
                    } 
                } else {
                	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                	response.getWriter().write("Errore:Non hai i poteri per fare questa operazione .");
                }
            } else {
            	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            	response.getWriter().write("Errore: utente non trovato con ID: " + idUtente);
            }
        } else {
        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        	response.getWriter().write("Nessun utente loggato trovato nella sessione.");
        }
	}

}
