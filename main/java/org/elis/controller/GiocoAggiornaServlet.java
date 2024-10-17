package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Genere;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

/**
 * Servlet implementation class GiocoAggiornaServlet
 */
@MultipartConfig(maxFileSize=1024*1024*20, maxRequestSize=1024*1024*50)
public class GiocoAggiornaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessione = request.getSession(false);
        if (sessione == null) {
            response.sendRedirect("public-jsp/PaginaLogin.jsp");
            return;
        }

        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        if (utente.getRuolo() != Ruolo.PUBLISHER) {
            response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
            return;
        }

        long idGioco = Long.parseLong(request.getParameter("giocoId"));
        String nomeGioco = request.getParameter("nome");
        String dataRilascio = request.getParameter("dataRilascio");
        String descrzioneGioco = request.getParameter("descrzione");
        String prezzo = request.getParameter("prezzo");
        Part filePart = request.getPart("immagine");
        String[] offerta = request.getParameterValues("offerta");
        String[] generi = request.getParameterValues("genere");

     

        // Aggiornamento nome
        if (nomeGioco != null && !nomeGioco.isEmpty()) {
            BusinessLogic.updateGiocoNome(idGioco, nomeGioco);
            System.out.println("Nome aggiornato con successo");
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            
        }

        // Aggiornamento data di rilascio
        if (dataRilascio != null && !dataRilascio.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDateTime = LocalDate.parse(dataRilascio, formatter);
                BusinessLogic.updateGiocoDataRilascio(idGioco, localDateTime);
                System.out.println("Data di rilascio aggiornata con successo");
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                
            } catch (DateTimeParseException e) {
                request.setAttribute("errorMessage", "Formato di data non valido.");
                request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
                return;
            }
        }

        // Aggiornamento descrizione
        if (descrzioneGioco != null && !descrzioneGioco.isEmpty()) {
            BusinessLogic.updateGiocoDescrzione(idGioco, descrzioneGioco);
            System.out.println("Descrizione aggiornata con successo");
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
        }

        // Ricezione e aggiornamento immagine
        
        if (filePart != null && filePart.getSize() > 0) {
            byte[] byteImmagine = filePart.getInputStream().readAllBytes();
            BusinessLogic.updateGiocoImmagine(idGioco, byteImmagine);
            System.out.println("Immagine aggiornata con successo");
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            
        }

        // Aggiornamento prezzo
        if (prezzo != null && !prezzo.isEmpty()) {
            try {
                double prezzoGioco = Double.parseDouble(prezzo);
                BusinessLogic.updateGiocoPrezzo(idGioco, prezzoGioco);
                System.out.println("Prezzo aggiornato con successo");
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Formato del prezzo non valido.");
                request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
                return;
            }
        }

        // Gestione dell'offerta
        if (offerta != null && offerta.length > 0) {
            try {
                long idOfferta = Long.parseLong(offerta[0]);
                Offerta offertaObj = BusinessLogic.findOffertaById(idOfferta);
                // Associa l'offerta al gioco
                // BusinessLogic.associaOffertaAGioco(idGioco, offertaObj);
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                System.out.println("Offerta aggiornata con successo");
            } catch (NumberFormatException e) {
                System.out.println("Errore nel formato dell'ID dell'offerta: " + e.getMessage());
                request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
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
                    if (genere != null) {
                        generiList.add(genere);
                        // Associa il genere al gioco
                         BusinessLogic.aggiungiGiocoaGnere(idGenere, idGioco);
                         request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Errore nel formato dell'ID del genere: " + e.getMessage());
                    request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
                    return;
                }
            }
        }

        
       
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
    
    }
}
