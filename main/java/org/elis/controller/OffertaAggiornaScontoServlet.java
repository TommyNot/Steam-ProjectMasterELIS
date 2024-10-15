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
		
		
		double nuovoSconto;
	    try {
            nuovoSconto = Double.parseDouble(sconto);
            if (nuovoSconto <= 0) {
                System.out.println("errore sconto minore di 0");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore nel formato dello sconto: " + e.getMessage());
            request.getRequestDispatcher("public-jsp/DashboardAdmin.jsp").forward(request, response);
            System.out.println("Errore nello sconto");
            return;
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
                    Offerta aggiornata = BusinessLogic.updateScontoOfferta(idUtente, nuovoSconto);
                    if (aggiornata != null) {
                        response.sendRedirect("public-jsp/DashboardAdmin.jsp");
                    } 
                } else {
                    System.out.println("L'utente non è un Admin.");
                }
            } else {
                System.out.println("Errore: utente non trovato con ID: " + idUtente);
            }
        } else {
            System.out.println("Nessun utente loggato trovato nella sessione.");
        }
	}

}
