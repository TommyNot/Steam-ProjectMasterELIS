package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Genere;
import org.elis.model.Gioco;

/**
 * Servlet implementation class GiocoGenereRicercaServlet
 */
public class GiocoGenereRicercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoGenereRicercaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] generiSelezionati = request.getParameterValues("genere");

        if (generiSelezionati != null && generiSelezionati.length > 0) {
            List<Genere> generi = new ArrayList<>();
            for (String nomeGenere : generiSelezionati) {
                
                Genere genere = new Genere();
                genere.setNome(nomeGenere); 
                generi.add(genere);
            }

            List<Gioco> giochiTrovati = BusinessLogic.GiocoCercaPerGenere((Genere) generi);
            request.setAttribute("giochi", giochiTrovati);
            request.getRequestDispatcher("public-jsp/PaginaGioco.jsp").forward(request, response);
            
        } else {
        	
            request.setAttribute("Error", "Seleziona almeno un genere.");
            request.getRequestDispatcher("public-jsp/paginaRicerca.jsp").forward(request, response);
        }
    }



}
