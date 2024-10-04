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
        String dataRilascio = request.getParameter("dataRilascio"); // da convertire in LocalDateTime
        String descrizione = request.getParameter("descrizione");
        String immagine = request.getParameter("immagine");
        String prezzo = request.getParameter("prezzo");
        String[] offerta = request.getParameterValues("offerta"); // frontend checkbox
        String[] generi = request.getParameterValues("generi");

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

        // Gestione dell'offerta
        Offerta offertaObj = null;
        if (offerta != null && offerta.length > 0) {
            try {
                long idOfferta = Long.parseLong(offerta[0]);
                offertaObj = BusinessLogic.findOffertaById(idOfferta);
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "Errore nel formato dell'ID dell'offerta: " + e.getMessage());
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                System.out.println("Gestione errore offerta");
                return;
            }
        }

        // Gestione dei generi
        List<Genere> generiList = new ArrayList<>();
        if (generi != null) {
            for (String genereId : generi) {
                try {
                    long idGenere = Long.parseLong(genereId);
                    Genere genere = BusinessLogic.getGenereById(idGenere);
                    generiList.add(genere);
                } catch (NumberFormatException e) {
                    request.setAttribute("errore", "Errore nel formato dell'ID del genere: " + e.getMessage());
                    request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                    System.out.println("Errore nel genere lista");
                    return;
                }
            }
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
            Gioco aggiunto = BusinessLogic.GiocoAdd(nome, data, descrizione, immagine, prezzoDouble, generiList, offertaObj, idUtente);
            if (aggiunto != null) {
                response.sendRedirect("successPage.jsp");
            } else {
                request.setAttribute("errore", "Errore nell'aggiunta del gioco.");
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errore", "Accesso non autorizzato.");
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            System.out.println("errore nell accesso");
        }
    }

		
	

}
