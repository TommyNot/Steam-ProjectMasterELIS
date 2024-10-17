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

@MultipartConfig(maxFileSize=1024*1024*20, maxRequestSize=1024*1024*50)
public class GiocoAggiungiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GiocoAggiungiServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessione = request.getSession(false);

        String nome = request.getParameter("nome");
        String dataRilascio = request.getParameter("dataRilascio");
        String descrizione = request.getParameter("descrizione");
        String prezzo = request.getParameter("prezzo");
        String offerta = request.getParameter("offerta");
        String generi = request.getParameter("genereId");

        // Controllo campi obbligatori
        if (nome == null || nome.isEmpty() || dataRilascio == null || descrizione == null || prezzo == null) {
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
                return;
            }
        }

        double prezzoDouble;
        try {
            prezzoDouble = Double.parseDouble(prezzo);
            if (prezzoDouble <= 0) {
                request.setAttribute("errore", "Il prezzo deve essere maggiore di zero.");
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore nel formato del prezzo: " + e.getMessage());
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            return;
        }

        Offerta offertaSelezionata = null;
        if (offerta != null) {
            try {
                long offertaId = Long.parseLong(offerta);
                offertaSelezionata = BusinessLogic.findOffertaById(offertaId);
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "Errore nel formato dell'offerta: " + e.getMessage());
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                return;
            }
        }

        // Controllo sessione
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }

        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");

        if (utente != null) {
            long idUtente1 = utente.getId();
            Utente u = BusinessLogic.UtenteFindById(idUtente1);

            if (u != null && u.getRuolo() == Ruolo.PUBLISHER) {
                // Ricevi il file immagine
                Part filePart = request.getPart("immagine"); // Assicurati di avere l'input appropriato nel tuo form
                String immagineBase64 = null;

                if (filePart != null && filePart.getSize() > 0) {
                    InputStream inputStream = filePart.getInputStream();
                    byte[] byteImmagine = inputStream.readAllBytes(); // Leggi i byte dell'immagine
                    immagineBase64 = Base64.getEncoder().encodeToString(byteImmagine); // Convertila in Base64
                }

                List<Genere> generiSelezionati = new ArrayList<>();
                if (generi != null) {
                    try {
                        long genereId = Long.parseLong(generi);
                        Genere genere = BusinessLogic.getGenereById(genereId);
                        if (genere != null) {
                            generiSelezionati.add(genere);
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("errore", "Errore nel formato del genere: " + e.getMessage());
                        request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                        return;
                    }
                }

                // Creazione del Gioco
                Gioco g = new Gioco(
                    0, // id verrà generato automaticamente
                    LocalDateTime.now(), // data_creazione
                    LocalDateTime.now(), // data_ultima_modifica
                    nome, // nome
                    data, // data_rilascio
                    descrizione, // descrizione
                    immagineBase64 != null ? Base64.getDecoder().decode(immagineBase64) : null, // byte_immagine
                    false, // eliminato
                    prezzoDouble, // prezzo
                    offertaSelezionata, // offertaGioco
                    u // utente
                );
                g.setGenereGiochi(generiSelezionati);

                // Aggiungi il gioco al database
                Gioco aggiunto = BusinessLogic.GiocoAdd(g);
                if (aggiunto != null) {
                    String successAddGioco = "Gioco aggiunto con successo";
                    request.setAttribute("addGioco", successAddGioco);
                    request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                } else {
                    request.setAttribute("errore", "Errore nell'aggiunta del gioco.");
                    request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                }
            }
        }
    }
}
