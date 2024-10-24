package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Libreria;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


@WebServlet("/OffertaAggiornaDataInizioServlet")
public class OffertaAggiornaDataInizioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public OffertaAggiornaDataInizioServlet() {
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
		
		System.out.println("sono dentro la servlet");
		
		String nuovaDataInizio = request.getParameter("nuovaDataInizio");
		String id = request.getParameter("id");
		

	      LocalDate nuovaData = null;
	        if (nuovaDataInizio != null && !nuovaDataInizio.isEmpty()) {
	            try {
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                nuovaData = LocalDate.parse(nuovaDataInizio, formatter); 
	            } catch (DateTimeParseException e) {
	                request.setAttribute("errore", "Errore nella formattazione della data e ora: " + e.getMessage());
	                request.getRequestDispatcher("WEB-INF/private-jsp/DashboardAdmin.jsp").forward(request, response);
	                System.out.println("Errore nella formattazione della data e ora");
	                return; 
	            }
	        }
	        
	    	if (id == null || id.isBlank()) {
	    		System.out.println("sono dentro il controllo id");
	            String errore = "L'id  dell'offerta non può essere vuoto.";
	            request.setAttribute("errore", errore); 
	            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardAdmin.jsp").forward(request, response);
	            return; 
	        }
		
	    long idOfferta = 0;
		  
			  try {
		        	
		        	idOfferta = Long.parseLong(id);
		        	
		        }catch(Exception e) {
		        	
		        	System.out.println("errore");
		        }

		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		if(utente != null) {
			long idUtente = utente.getId();
			
			Utente u = BusinessLogic.UtenteFindById(idUtente);
			if(u != null) {
				boolean isAdmin = u.getRuolo() == Ruolo.ADMIN;
				if(isAdmin) {
					Offerta OffertaNuovaDataInizio = BusinessLogic.updateDataInizioOfferta(idOfferta, nuovaData);
					
					if(OffertaNuovaDataInizio != null) {
						System.out.println("data aggiornata");
						response.getWriter().write("Data aggiornata con successo.");
					}else {
						request.getRequestDispatcher("public-jsp/ErrorPage.jsp");
						return;
					}
				}else {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                	response.getWriter().write("Errore:Non hai i poteri per fare questa operazione .");
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
