package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Genere;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

/**
 * Servlet implementation class AggiornaGiocoServlet
 */
public class GiocoAggiornaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoAggiornaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession sessione = request.getSession(false);
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }
        


        Utente utente = (Utente) sessione.getAttribute("utente");
        if (utente == null || utente.getRuolo() != Ruolo.PUBLISHER) {
            response.sendRedirect("public-jsp/AccessoNegato.jsp");
            return;
        }
        boolean isPublisher = false;
        
        long idGioco = Long.parseLong(request.getParameter("giocoId"));
        String nomeGioco = request.getParameter("nome");
        String dataRilascio = request.getParameter("dataRilascio");
        String descrzioneGioco = request.getParameter("descrzione");
        String immagineGioco = request.getParameter("immagine");
        String prezzo = request.getParameter("prezzo");
        String[] offerta = request.getParameterValues("offerta");
        String[] generi = request.getParameterValues("generi");
        

   
        boolean aggiornato = false;

        if (nomeGioco != null && !nomeGioco.isEmpty()) {
            
            BusinessLogic.updateGiocoNome(idGioco,nomeGioco);
            aggiornato = true;
        }

        if (dataRilascio != null && !dataRilascio.isEmpty()) {
            LocalDateTime localDateTime;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                localDateTime = LocalDateTime.parse(dataRilascio, formatter);
                BusinessLogic.updateGiocoDataRilascio(idGioco,localDateTime);
                aggiornato = true;
            } catch (DateTimeParseException e) {
                request.setAttribute("errorMessage", "Formato di data non valido.");
                request.getRequestDispatcher("WEB-INF/public-jsp/error.jsp").forward(request, response);
                return;
            }
        }

        if (descrzioneGioco != null && !descrzioneGioco.isEmpty()) {
            BusinessLogic.updateGiocoDescrzione(idGioco, descrzioneGioco);
            aggiornato = true;
        }

        if (immagineGioco != null && !immagineGioco.isEmpty()) {
            BusinessLogic.updateGiocoImmagine(idGioco, immagineGioco);
            aggiornato = true;
        }

        if (prezzo != null && !prezzo.isEmpty()) {
            double prezzoGioco;
            try {
                prezzoGioco = Double.parseDouble(prezzo);
                BusinessLogic.updateGiocoPrezzo(idGioco,prezzoGioco);
                aggiornato = true;
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Formato del prezzo non valido.");
                request.getRequestDispatcher("WEB-INF/public-jsp/error.jsp").forward(request, response);
                return;
            }
        }
        
        // Gestione dell'offerta
        Offerta offertaObj = null;
        if (offerta != null && offerta.length > 0) {
            try {
                long idOfferta = Long.parseLong(offerta[0]); 
                offertaObj = BusinessLogic.findOffertaById(idOfferta);
            } catch (NumberFormatException e) {
                System.out.println("Errore nel formato dell'ID dell'offerta: " + e.getMessage());
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
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Errore nel formato dell'ID del genere: " + e.getMessage());
                    return;
                }
            }
        }

     
		
		
			
			
		}

		
	}


