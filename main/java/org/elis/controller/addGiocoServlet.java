package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Time;
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
 * Servlet implementation class addGiocoServlet
 */
public class addGiocoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addGiocoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessione = request.getSession(false);
		
		//prendo parametri dal page html publisher per settare addGioco
		
		String nome = request.getParameter("nome");
		String dataRilascio = request.getParameter("dataRilascio");//da convertire in LocalDateTime
		String descrizione = request.getParameter("descrizione");
		String immagine = request.getParameter("immagine");
		
		String prezzo = request.getParameter("prezzo");
		String[] offerta = request.getParameterValues("offerta");//frontend checkbox da fare
		String[] generi = request.getParameterValues("generi");
		
		long idUtente = 0;
		
		boolean isPublisher = false;
		
		
		LocalDateTime localDateTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            localDateTime = LocalDateTime.parse(dataRilascio, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Errore nella formattazione della data: " + e.getMessage());
            return;
        }

        double prezzoDouble;
        try {
            prezzoDouble = Double.parseDouble(prezzo);
        } catch (NumberFormatException e) {
            System.out.println("Errore nel formato del prezzo: " + e.getMessage());
            return;
        }
        
        


        // Gestione dell'offerta
        Offerta offertaObj = null; //
        if (offerta != null && offerta.length > 0) {
            try {
                long idOfferta = Long.parseLong(offerta[0]); // supponendo che sia un singolo ID
                offertaObj = BusinessLogic.findOffertaById(idOfferta); // metodo che recupera l'offerta da fare dic
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
                    Genere genere = BusinessLogic.getGenereById(idGenere);//mi serve bussines logic genere by id
                    generiList.add(genere);
                } catch (NumberFormatException e) {
                    System.out.println("Errore nel formato dell'ID del genere: " + e.getMessage());
                    return;
                }
            }
        }
		
		if(sessione == null) {
			
			response.sendRedirect("public-jsp/LoginPage.jsp");
			return;
		}
		
		Object obj = sessione.getAttribute("utente");
		
		if(obj instanceof Utente) {
			
			Utente utente = (Utente)obj;
			
			idUtente = utente.getId();
			
			 isPublisher =  utente.getRuolo() == Ruolo.PUBLISHER;
		}
		
		if(isPublisher) {
			
			BusinessLogic.GiocoAdd(nome,localDateTime, descrizione,immagine, prezzoDouble, generiList, offertaObj, idUtente);
		}
		
		
	}

}
