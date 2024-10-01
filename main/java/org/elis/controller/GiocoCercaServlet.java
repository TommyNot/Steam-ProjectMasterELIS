package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;

/**
 * Servlet implementation class GiocoCercaServlet
 */
public class GiocoCercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoCercaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ricerca = request.getParameter("searchBarInput");
		
		if(ricerca == null || ricerca.isBlank()) {
			String error = "campo vuoto";
			request.setAttribute("campo vuoto", error);
			request.getRequestDispatcher("public-jsp/HomePagePrincipale.jsp").forward(request, response);
			return;
		}
		
		Gioco search = BusinessLogic.TrovaByName(ricerca);
		
		if(search == null) {
	        String errorMessage = "Nessun gioco trovato con il nome: " + ricerca;
	        request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("public-jsp/HomePagePrincipale.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("gioco", search);
		request.getRequestDispatcher("public-jsp/PaginaGioco.jsp").forward(request, response);
		
		
		
	}

}
