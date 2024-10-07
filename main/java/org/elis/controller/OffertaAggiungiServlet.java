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
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


@WebServlet("/OffertaAggiungiServlet")
public class OffertaAggiungiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OffertaAggiungiServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession(false);
		
		String nome = request.getParameter("nome");
		String sconto = request.getParameter("sconto");
		String data_inizio = request.getParameter("data_inizio");
		String data_fine = request.getParameter("data_fine");
		
		
		
		
        if (nome == null || nome.isEmpty() || sconto == null || data_inizio == null || data_fine == null ) {
            request.setAttribute("errore", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardAdmin.jsp").forward(request, response);
            return;
        }
		
        LocalDateTime inizio_offerta = null;
        if (data_inizio != null && !data_inizio.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                inizio_offerta = LocalDateTime.parse(data_inizio, formatter); 
            } catch (DateTimeParseException e) {
                request.setAttribute("errore", "Errore nella formattazione della data e ora: " + e.getMessage());
                request.getRequestDispatcher("public-jsp/DashboardAdmin.jsp").forward(request, response);
                System.out.println("Errore nella formattazione della data e ora");
                return; 
            }
        }

		
        LocalDateTime fine_offerta = null;
        if (data_fine != null && !data_fine.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH: mm:ss");
                fine_offerta = LocalDateTime.parse(data_fine, formatter); 
            } catch (DateTimeParseException e) {
                request.setAttribute("errore", "Errore nella formattazione della data: " + e.getMessage());
                request.getRequestDispatcher("public-jsp/DashboardAdmin.jsp").forward(request, response);
                System.out.println("errore nella data");
                return; 
            }
        }
        
        double scontoDouble;
        try {
            scontoDouble = Double.parseDouble(sconto);
            if (scontoDouble <= 0) {
                System.out.println("errore sconto minore di 0");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore nel formato dello sconto: " + e.getMessage());
            request.getRequestDispatcher("public-jsp/DashboardAdmin.jsp").forward(request, response);
            System.out.println("Errore nello sconto");
            return;
        }
        
        
        // Controllo sulla sessione
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }

        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        
        
       //Controllo sull'utente
        if (utente != null) {
            long idUtente =  utente.getId();
            System.out.println("ID Utente loggato: " + idUtente);
            
            Utente u = BusinessLogic.UtenteFindById(idUtente);
            if (u != null) {
                boolean isAdmin= u.getRuolo() == Ruolo.ADMIN;
                if (isAdmin) {
                    System.out.println("L'utente è un Admin.");
                    Offerta aggiunta = BusinessLogic.offertaAdd(nome, scontoDouble, inizio_offerta, fine_offerta);
                    if (aggiunta != null) {
                        response.sendRedirect("successPage.jsp");
                    } else {
                        request.setAttribute("errore", "Errore nell'aggiunta dell'offerta.");
                        request.getRequestDispatcher("public-jsp/DashboardAdmin.jsp").forward(request, response);
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
