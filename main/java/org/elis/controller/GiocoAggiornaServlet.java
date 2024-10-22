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
import org.elis.model.Gioco;
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
        
        String successoModi = "Gioco aggiornato con successo";

        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        if (utente == null || utente.getRuolo() != Ruolo.PUBLISHER) {
            response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
            return;
        }

        try {
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
                request.setAttribute("giocoModifica", successoModi);
            }

            // Aggiornamento data di rilascio
            if (dataRilascio != null && !dataRilascio.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    LocalDate localDate = LocalDate.parse(dataRilascio, formatter);
                    BusinessLogic.updateGiocoDataRilascio(idGioco, localDate);
                    System.out.println("Data di rilascio aggiornata con successo");
                    request.setAttribute("giocoModifica", successoModi);
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
                request.setAttribute("giocoModifica", successoModi);
            }

            // Ricezione e aggiornamento immagine
            if (filePart != null && filePart.getSize() > 0) {
                byte[] byteImmagine = filePart.getInputStream().readAllBytes();
                BusinessLogic.updateGiocoImmagine(idGioco, byteImmagine);
                request.setAttribute("giocoModifica", successoModi);
                System.out.println("Immagine aggiornata con successo");
            }

            // Aggiornamento prezzo
            if (prezzo != null && !prezzo.isEmpty()) {
                try {
                    double prezzoGioco = Double.parseDouble(prezzo);
                    BusinessLogic.updateGiocoPrezzo(idGioco, prezzoGioco);
                    System.out.println("Prezzo aggiornato con successo");
                    request.setAttribute("giocoModifica", successoModi);
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Formato del prezzo non valido.");
                    request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
                    return;
                }
            }

            if (offerta != null) {
                for (String offertaId : offerta) {
                    try {
                    	System.out.println("Siamo qui ?");
                        long idOfferta = Long.parseLong(offertaId);
                        
                        Offerta offert = BusinessLogic.findOffertaById(idOfferta);
                        if(offert != null) {
                            BusinessLogic.updateGiocoOfferta(idGioco, offert.getId());
                            request.setAttribute("giocoModifica", successoModi);
                            System.out.println("Offerta aggiornata con successo");
                        }else {
                        	BusinessLogic.updateGiocoOfferta(idGioco, (Long) null );
                        	System.out.println("Qui errore");
                        }
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Errore nel formato dell'ID dell'offerta: " + e.getMessage());
                        request.setAttribute("errorMessage", "Formato dell'ID dell'offerta non valido.");
                    }
                }
            }
            // Gestione dei generi
            if (generi != null) {
            	
                // Prima rimuoviamo i generi già associati al gioco
                BusinessLogic.rimuoviGeneriDaGioco(idGioco);
                for (String genereId : generi) {
                    try {
                        long idGenere = Long.parseLong(genereId);
                        Genere genere = BusinessLogic.getGenereById(idGenere);
                        if (genere != null) {
                        	//associo il gioco genere , ora funziona
                            BusinessLogic.aggiungiGiocoaGnere(idGenere, idGioco);
                            
                            request.setAttribute("giocoModifica", successoModi);
                            System.out.println("Genere associato con successo");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Errore nel formato dell'ID del genere: " + e.getMessage());
                        request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
                        return;
                    }
                }
            }

            // Dopo aver aggiornato tutti i campi, vediamo se funziona cosi
            request.setAttribute("giocoModifica", successoModi);
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("Errore nel formato dell'ID del gioco: " + e.getMessage());
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
        }
    }

}
