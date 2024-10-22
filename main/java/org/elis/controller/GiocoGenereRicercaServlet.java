package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;
import org.elis.model.Genere; // Import your Genere model

public class GiocoGenereRicercaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GiocoGenereRicercaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String generiSelezionati = request.getParameter("genereId");

        if (generiSelezionati == null || generiSelezionati.isEmpty()) {
            request.setAttribute("Error", "Seleziona almeno un genere.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            System.out.println("Errore qui in genere");
            return;               
        }

        long idGenere;
        try {
            idGenere = Long.parseLong(generiSelezionati);
        } catch (NumberFormatException e) {
            e.printStackTrace(); 
            request.setAttribute("Error", "Errore nel formato del genere selezionato.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            System.out.println("errore qui nel try");
            return;
        }

        
        Genere genere = BusinessLogic.getGenereById(idGenere);
        if (genere == null) {
            request.setAttribute("Error", "Genere non trovato.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            System.out.println("Errore qui in generre");
            return;
        }

        List<Gioco> giochiTrovati = BusinessLogic.GiocoCercaPerGenere(idGenere);

        if (giochiTrovati == null || giochiTrovati.isEmpty()) {
        	String errore = "Nessun Gioco con questo genere è disponibili al momento";
            request.setAttribute("Error", errore);
            request.getRequestDispatcher("public-jsp/PageGiochi.jsp").forward(request, response);
            System.out.println("errore qui in giochi.trovati");
            return;
        }
        
        request.setAttribute("giochi", giochiTrovati);
        request.getRequestDispatcher("public-jsp/PageGiochi.jsp").forward(request, response);
    }
}
