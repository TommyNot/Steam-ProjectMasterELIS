package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Libreria;

/**
 * Servlet implementation class LibreriaFindByNameServlet
 */
public class LibreriaFindByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LibreriaFindByNameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ricerca = request.getParameter("nomeLibreriaInput");
		
		if(ricerca == null || ricerca.isBlank()) {
			String messaggioErrore = "campo di ricerca vuoto";
			request.setAttribute("messaggioErrore", messaggioErrore);
			return;
		}
		
		Libreria nomeCercato = BusinessLogic.findLibreriaByNome(ricerca);
		
		if(nomeCercato == null){
			String errore = "Nessuna libreria trovata con il nome " + ricerca;
			request.setAttribute("errore", errore);
			return;
		}
	}

}
