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
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

@WebServlet("/OffertaEliminaServlet")
public class OffertaEliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public OffertaEliminaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String eliminaOffertaNome = request.getParameter("nome");
        String id = request.getParameter("id");
		
		
		  if (eliminaOffertaNome == null || eliminaOffertaNome.isBlank() || id == null || id.isBlank()) {
	            String errore = "Il nome o l'id dell'offerta non può essere vuoto.";
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
		
		HttpSession sessione = request.getSession(false);
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }
        
        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        if (utente == null || utente.getRuolo() != Ruolo.ADMIN) {
            response.sendRedirect("public-jsp/ErrorPage.jsp");
            return;
        }
        
       
        if (utente != null) {
            long idUtente =  utente.getId();
            System.out.println("ID Utente loggato: " + idUtente);
            
            Utente u = BusinessLogic.UtenteFindById(idUtente);
            if (u != null) {
                boolean isAdmin= u.getRuolo() == Ruolo.ADMIN;
                if (isAdmin) {
                    System.out.println("L'utente è un Admin.");
                   List <Gioco> g=BusinessLogic.VisualizzaGiochiInOfferta();
                   List<Genere>ge=BusinessLogic.VisalizzaTuttiGeneri();
                   for(Gioco giochi:g) {
                	   if(giochi.getOffertaGioco().getId()==idOfferta) {
                		   BusinessLogic.rimuoviGiocoOfferta(giochi.getId());
                	   }
                   }
                   for(Genere generi:ge) {
                	   if(generi.getOffertaGenere().getId()==idOfferta) {
                		   BusinessLogic.removeGenereOfferta(generi.getOffertaGenere().getId(), idOfferta);
                	   }
                   }
                    Offerta eliminata = BusinessLogic.deleteByNameOfferta( eliminaOffertaNome, idOfferta);
                    if (eliminata != null) {
                    	response.getWriter().write("Offerta eliminata con successo.");
                    } 
                } else {
                	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                	response.getWriter().write("Errore:Non hai i poteri per fare questa operazione .");
                }
            } else {
            	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            	response.getWriter().write("Errore: utente non trovato con ID: " + idUtente);
            }
        } 
	}
}
