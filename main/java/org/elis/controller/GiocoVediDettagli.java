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
import org.elis.model.Gioco;
import org.elis.model.Libreria;
import org.elis.model.Utente;

/**
 * Servlet implementation class GiocoVediDettagli
 */
public class GiocoVediDettagli extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoVediDettagli() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ricerca = request.getParameter("barraRicerca");
        
        HttpSession sessione = request.getSession(false);
        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        
        
        if (ricerca == null || ricerca.isBlank()) {
            String error = "Il campo di ricerca è vuoto";
            request.setAttribute("errorMessage", error);
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            System.out.println("Errore: campo di ricerca vuoto");
            return;
        }

        
        long idGioco;
        try {
            idGioco = Long.parseLong(ricerca);
        } catch (NumberFormatException e) {
            String errorMessage = "L'ID del gioco non è valido.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }
        
        
        Gioco search = BusinessLogic.findGiocoById(idGioco);
        
        if (search == null) {
            String errorMessage = "Nessun gioco trovato con l'ID: " + ricerca;
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }
        
        
        if (utente != null) {
            List<Libreria> librerieUtente = BusinessLogic.findLibreriaByIdUtente(utente.getId());
            request.setAttribute("libreriaUtente", librerieUtente);
        }

       
        request.setAttribute("giochi", search);
        request.getRequestDispatcher("public-jsp/DettagliGioco.jsp").forward(request, response);
    }
}
