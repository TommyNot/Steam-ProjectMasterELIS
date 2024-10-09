package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Libreria;

public class LibreriaListaCompletaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibreriaListaCompletaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Libreria> librerie = BusinessLogic.VisualizzaTutteLibrerie();
		
		if(librerie == null || librerie.isEmpty()) {
			request.setAttribute("errorMessage", "Nessun gioco disponibile.");
			request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
		}
	}

}
