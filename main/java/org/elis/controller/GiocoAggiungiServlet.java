package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

/**
 * Servlet implementation class addGiocoServlet
 */
public class GiocoAggiungiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoAggiungiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessione = request.getSession(false);

        
        String nome = request.getParameter("nome");
        String dataRilascio = request.getParameter("dataRilascio"); 
        String descrizione = request.getParameter("descrizione");
        String immagine = request.getParameter("immagine");
        String prezzo = request.getParameter("prezzo");
        String offerta = request.getParameter("offerta"); 
        String generi = request.getParameter("genere");

        long idUtente = 0;
        boolean isPublisher = false;

        
        if (nome == null || nome.isEmpty() || dataRilascio == null || descrizione == null || immagine == null || prezzo == null) {
            request.setAttribute("errore", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
            return;
        }
        
        LocalDate data = null;
        if (dataRilascio != null && !dataRilascio.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                data = LocalDate.parse(dataRilascio, formatter); 
            } catch (DateTimeParseException e) {
                request.setAttribute("errore", "Errore nella formattazione della data: " + e.getMessage());
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                System.out.println("errore nella data");
                return; 
            }
        }

        double prezzoDouble;
        try {
            prezzoDouble = Double.parseDouble(prezzo);
            if (prezzoDouble <= 0) {
                System.out.println("errore prezzo minore di 0");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore nel formato del prezzo: " + e.getMessage());
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            System.out.println("Errore nel prezzo");
            return;
        }

      
        
        Genere genereSelezionato = null;
        // Logica per i generi
        if (generi != null && !generi.isEmpty()) {
            long genereId= Long.parseLong(generi);
            // Recupera l'oggetto Offerta utilizzando la logica di business
            genereSelezionato = BusinessLogic.getGenereById(genereId);
            request.setAttribute("genereSelezionato", genereSelezionato);
            System.out.println("errrore genere");
        }
        
        Offerta offertaSelezionata = null;

        if (offerta != null && !offerta.isEmpty()) {
            long offertaId = Long.parseLong(offerta);
            // Recupera l'oggetto Offerta utilizzando la logica di business
            offertaSelezionata = BusinessLogic.findOffertaById(offertaId);
            request.setAttribute("offertaSelezionata", offertaSelezionata); 
            System.out.println("errore offerta");
            
        }


        // Controllo sessione
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }

        Object obj = sessione.getAttribute("utente");

        if (obj instanceof Utente) {
            Utente utente = (Utente) obj;
            idUtente = utente.getId();
            isPublisher = utente.getRuolo() == Ruolo.PUBLISHER;
        }

        if (isPublisher) {
            Gioco aggiunto = BusinessLogic.GiocoAdd(nome, data, descrizione, immagine, prezzoDouble, genereSelezionato , offertaSelezionata, idUtente);
            if (aggiunto != null) {
                response.sendRedirect("successPage.jsp");
            } else {
                request.setAttribute("errore", "Errore nell'aggiunta del gioco.");
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            }
        } 
    }

		
	

}
